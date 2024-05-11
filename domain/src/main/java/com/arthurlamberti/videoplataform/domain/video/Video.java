package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.utils.InstantUtils;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.Year;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class Video extends AggregateRoot<VideoID> {

    private String title;
    private String description;
    private Year launchedAt;
    private double duration;
    private Rating rating;
    private boolean opened;
    private boolean published;
    private Instant createdAt;
    private Instant updatedAt;

    private ImageMedia banner;
    private ImageMedia thumbnail;
    private ImageMedia thumbnailHalf;
    private AudioVideoMedia trailer;
    private AudioVideoMedia video;
    private Set<CategoryID> categories;
    private Set<GenreID> genres;
    private Set<CastMemberID> castmembers;

    public Video(
            final VideoID anId,
            final String title,
            final String description,
            final Year launchedAt,
            final double duration,
            final Rating rating,
            final boolean opened,
            final boolean published,
            final Instant createdAt,
            final Instant updatedAt,
            final ImageMedia banner,
            final ImageMedia thumbnail,
            final ImageMedia thumbnailHalf,
            final AudioVideoMedia trailer,
            final AudioVideoMedia video,
            final Set<CategoryID> categories,
            final Set<GenreID> genres,
            final Set<CastMemberID> castmembers
    ) {
        super(anId);
        this.title = title;
        this.description = description;
        this.launchedAt = launchedAt;
        this.duration = duration;
        this.rating = rating;
        this.opened = opened;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.banner = banner;
        this.thumbnail = thumbnail;
        this.thumbnailHalf = thumbnailHalf;
        this.trailer = trailer;
        this.video = video;
        this.categories = categories;
        this.genres = genres;
        this.castmembers = castmembers;
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    public Optional<ImageMedia> getBanner() {
        return Optional.ofNullable(banner);
    }

    public Optional<ImageMedia> getThumbnail() {
        return Optional.ofNullable(thumbnail);
    }

    public Optional<ImageMedia> getThumbnailHalf() {
        return Optional.ofNullable(thumbnailHalf);
    }

    public Optional<AudioVideoMedia> getTrailer() {
        return Optional.ofNullable(trailer);
    }

    public Optional<AudioVideoMedia> getVideo() {
        return Optional.ofNullable(video);
    }

    public static Video newVideo(
            final String title,
            final String description,
            final Year launchedAt,
            final double duration,
            final Rating rating,
            final boolean opened,
            final boolean published,
            final Set<CategoryID> categories,
            final Set<GenreID> genres,
            final Set<CastMemberID> castmembers
    ) {
        final var anId = VideoID.unique();
        final var now = InstantUtils.now();
        return new Video(
                anId,
                title,
                description,
                launchedAt,
                duration,
                rating,
                opened,
                published,
                now,
                now,
                null,
                null,
                null,
                null,
                null,
                categories,
                genres,
                castmembers
        );
    }

    public Set<CategoryID> getCategories() {
        return nonNull(categories) ? Collections.unmodifiableSet(categories) : Collections.emptySet();
    }

    public Set<GenreID> getGenres() {
        return nonNull(genres) ? Collections.unmodifiableSet(genres) : Collections.emptySet();
    }

    public Set<CastMemberID> getCastmembers() {
        return nonNull(castmembers) ? Collections.unmodifiableSet(castmembers) : Collections.emptySet();
    }

    public void setCategories(Set<CategoryID> categories) {
        this.categories = categories != null ? new HashSet<>(categories) : Collections.emptySet();
    }

    public void setGenres(Set<GenreID> genres) {
        this.genres = genres != null ? new HashSet<>(genres) : Collections.emptySet();
    }

    public Video setThumbnail(final ImageMedia thumbnail) {
        this.thumbnail = thumbnail;
        this.updatedAt = InstantUtils.now();
        return this;
    }
    public Video setThumbnailHalf(final ImageMedia thumbnailHalf) {
        this.thumbnailHalf = thumbnailHalf;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Video setTrailer(final AudioVideoMedia trailer) {
        this.trailer = trailer;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Video setVideo(final AudioVideoMedia video) {
        this.video = video;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    private void setCastmembers(Set<CastMemberID> castmembers) {
        this.castmembers = castmembers != null ? new HashSet<>(castmembers) : Collections.emptySet();
    }

    public Video setBanner(final ImageMedia banner) {
        this.banner = banner;
        this.updatedAt = Instant.now();
        return this;
    }


    public static Video with(final Video video) {
        return new Video(
                video.getId(),
                video.getTitle(),
                video.getDescription(),
                video.getLaunchedAt(),
                video.getDuration(),
                video.getRating(),
                video.isOpened(),
                video.isPublished(),
                video.getCreatedAt(),
                video.getUpdatedAt(),
                video.getBanner().orElse(null),
                video.getThumbnail().orElse(null),
                video.getThumbnailHalf().orElse(null),
                video.getTrailer().orElse(null),
                video.getVideo().orElse(null),
                new HashSet<>(video.getCategories()),
                new HashSet<>(video.getGenres()),
                new HashSet<>(video.getCastmembers())
        );
    }

    public Video update(
            final String title,
            final String description,
            final Year launchedAt,
            final double duration,
            final Rating rating,
            final boolean opened,
            final boolean published,
            final Set<CategoryID> categories,
            final Set<GenreID> genres,
            final Set<CastMemberID> castmembers
    ) {
        this.title = title;
        this.description = description;
        this.launchedAt = launchedAt;
        this.duration = duration;
        this.rating = rating;
        this.opened = opened;
        this.published = published;
        this.setCastmembers(castmembers);
        this.setCategories(categories);
        this.setGenres(genres);
        this.updatedAt = InstantUtils.now();
        return this;
    }
}
