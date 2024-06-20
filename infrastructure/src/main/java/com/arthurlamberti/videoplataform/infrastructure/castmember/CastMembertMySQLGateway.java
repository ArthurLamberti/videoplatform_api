package com.arthurlamberti.videoplataform.infrastructure.castmember;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;
import com.arthurlamberti.videoplataform.infrastructure.castmember.persistence.CastMemberJpaEntity;
import com.arthurlamberti.videoplataform.infrastructure.castmember.persistence.CastMemberRepository;
import com.arthurlamberti.videoplataform.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.Objects.requireNonNull;

@Component
public class CastMembertMySQLGateway implements CastMemberGateway {
    private final CastMemberRepository castMemberRepository;

    public CastMembertMySQLGateway(CastMemberRepository castMemberRepository) {
        this.castMemberRepository = requireNonNull(castMemberRepository);
    }

    @Override
    public CastMember create(CastMember aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(CastMemberID castMemberID) {
        final var anId = castMemberID.getValue();
        if (this.castMemberRepository.existsById(anId)) {
            this.castMemberRepository.deleteById(anId);
        }
    }

    @Override
    public Optional<CastMember> findById(CastMemberID anId) {
        return this.castMemberRepository.findById(anId.getValue()).map(CastMemberJpaEntity::toAggregate);
    }

    @Override
    public CastMember update(CastMember aGenre) {
        return save(aGenre);
    }

    @Override
    public Pagination<CastMember> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );
        final var where = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);
        final var result = this.castMemberRepository.findAll(where, page);
        return  new Pagination<>(
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.map(CastMemberJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<CastMemberID> existsByIds(Iterable<CastMemberID> castMemberIDS) {
        final var ids = StreamSupport.stream(castMemberIDS.spliterator(), false)
                .map(CastMemberID::getValue)
                .toList();
        return this.castMemberRepository.existsByIds(ids).stream()
                .map(CastMemberID::from)
                .toList();
    }

    private CastMember save(final CastMember aCastMember) {
        return this.castMemberRepository.save(CastMemberJpaEntity.from(aCastMember)).toAggregate();
    }

    private Specification<CastMemberJpaEntity> assembleSpecification(final String term) {
        return SpecificationUtils.like("name", term);
    }
}
