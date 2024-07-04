package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.events.DomainEvent;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.arthurlamberti.videoplataform.domain.utils.InstantUtils;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.Year;
import java.util.*;

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
            final Set<CastMemberID> castmembers,
            final List<DomainEvent> events
    ) {
        super(anId, events);
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
        new VideoValidator(this, handler).validate();
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
                castmembers,
                null
        );
    }

    public Set<CategoryID> getCategories() {
        return nonNull(categories) ? Collections.unmodifiableSet(categories) : Collections.emptySet();
    }

    public Set<GenreID> getGenres() {
        return nonNull(genres) ? Collections.unmodifiableSet(genres) : Collections.emptySet();
    }

    public Set<CastMemberID> getCastMembers() {
        return nonNull(castmembers) ? Collections.unmodifiableSet(castmembers) : Collections.emptySet();
    }

    public void setCategories(Set<CategoryID> categories) {
        this.categories = categories != null ? new HashSet<>(categories) : Collections.emptySet();
    }

    public void setGenres(Set<GenreID> genres) {
        this.genres = genres != null ? new HashSet<>(genres) : Collections.emptySet();
    }

    public Video updateThumbnailMedia(final ImageMedia thumbnail) {
        this.thumbnail = thumbnail;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Video updateThumbnailHalfMedia(final ImageMedia thumbnailHalf) {
        this.thumbnailHalf = thumbnailHalf;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Video updateTrailerMedia(final AudioVideoMedia trailer) {
        this.trailer = trailer;
        this.updatedAt = InstantUtils.now();
        onAudioVideoMediaUpdated(trailer);
        return this;
    }

    public Video updateVideoMedia(final AudioVideoMedia video) {
        this.video = video;
        this.updatedAt = InstantUtils.now();

        onAudioVideoMediaUpdated(video);
        return this;
    }

    public Video updateBannerMedia(final ImageMedia banner) {
        this.banner = banner;
        this.updatedAt = Instant.now();
        return this;
    }


    private void setCastmembers(Set<CastMemberID> castmembers) {
        this.castmembers = castmembers != null ? new HashSet<>(castmembers) : Collections.emptySet();
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
                new HashSet<>(video.getCastMembers()),
                video.getDomainEvents()
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

    public static Video with(
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
        return new Video(
                anId,
                title,
                description,
                launchedAt,
                duration,
                rating,
                opened,
                published,
                createdAt,
                updatedAt,
                banner,
                thumbnail,
                thumbnailHalf,
                trailer,
                video,
                categories,
                genres,
                castmembers,
                null
        );
    }

    public Video processing(final VideoMediaType aType) {
        if (VideoMediaType.VIDEO == aType) {
            getVideo()
                    .ifPresent(media -> updateVideoMedia(media.processing()));
        } else if (VideoMediaType.TRAILER == aType) {
            getTrailer()
                    .ifPresent(media -> updateTrailerMedia(media.processing()));
        }

        return this;
    }

    public Video completed(final VideoMediaType aType, final String encodedPath) {
        if (VideoMediaType.VIDEO == aType) {
            getVideo()
                    .ifPresent(media -> updateVideoMedia(media.completed(encodedPath)));
        } else if (VideoMediaType.TRAILER == aType) {
            getTrailer()
                    .ifPresent(media -> updateTrailerMedia(media.completed(encodedPath)));
        }

        return this;
    }

    private void onAudioVideoMediaUpdated(AudioVideoMedia media) {
        if (media != null && media.isPendindEncode()) {
            this.registerEvent(new VideoMediaCreated(getId().getValue(), media.getRawLocation()));
        }
    }
}
