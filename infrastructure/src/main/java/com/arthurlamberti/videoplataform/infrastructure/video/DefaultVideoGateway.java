package com.arthurlamberti.videoplataform.infrastructure.video;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.utils.CollectionsUtils;
import com.arthurlamberti.videoplataform.domain.video.*;
import com.arthurlamberti.videoplataform.infrastructure.utils.SqlUtils;
import com.arthurlamberti.videoplataform.infrastructure.video.persistence.VideoJpaEntity;
import com.arthurlamberti.videoplataform.infrastructure.video.persistence.VideoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultVideoGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public DefaultVideoGateway(final VideoRepository videoRepository) {
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }

    @Override
    @Transactional
    public Video create(final Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo)).toAggregate();
    }

    @Override
    public void deleteById(final VideoID anId) {
        final var aVideoId = anId.getValue();
        if (this.videoRepository.existsById(aVideoId)) {
            this.videoRepository.deleteById(aVideoId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Video> findById(final VideoID anId) {
        return this.videoRepository.findById(anId.getValue()).map(VideoJpaEntity::toAggregate);
    }

    @Override
    @Transactional
    public Video update(final Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo)).toAggregate();
    }

    @Override
    public Pagination<VideoPreview> findAll(final VideoSearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );
        final var actualPage = this.videoRepository.findAll(
                SqlUtils.like(aQuery.terms()),
                CollectionsUtils.nullIfEmpty(CollectionsUtils.mapTo(aQuery.castMembers(), Identifier::getValue)),
                CollectionsUtils.nullIfEmpty(CollectionsUtils.mapTo(aQuery.categories(), Identifier::getValue)),
                CollectionsUtils.nullIfEmpty(CollectionsUtils.mapTo(aQuery.genres(), Identifier::getValue)),
                page
        );
        return new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }
}
