package com.arthurlamberti.videoplataform.application.video.create;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryGateway;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.exception.DomainException;
import com.arthurlamberti.videoplataform.domain.exception.InternalErrorException;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.arthurlamberti.videoplataform.domain.resource.VideoResource;
import com.arthurlamberti.videoplataform.domain.validation.Error;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;
import com.arthurlamberti.videoplataform.domain.video.MediaResourceGateway;
import com.arthurlamberti.videoplataform.domain.video.Rating;
import com.arthurlamberti.videoplataform.domain.video.Video;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class DefaultCreateVideoUseCase extends CreateVideoUseCase {

    private final VideoGateway videoGateway;
    private final CategoryGateway categoryGateway;
    private final GenreGateway genreGateway;
    private final CastMemberGateway castMemberGateway;
    private final MediaResourceGateway mediaResourceGateway;

    public DefaultCreateVideoUseCase(
            final VideoGateway videoGateway,
            final CategoryGateway categoryGateway,
            final GenreGateway genreGateway,
            final CastMemberGateway castMemberGateway,
            final MediaResourceGateway mediaResourceGateway) {
        this.videoGateway = requireNonNull(videoGateway);
        this.categoryGateway = requireNonNull(categoryGateway);
        this.genreGateway = requireNonNull(genreGateway);
        this.castMemberGateway = requireNonNull(castMemberGateway);
        this.mediaResourceGateway = requireNonNull(mediaResourceGateway);
    }

    @Override
    public CreateVideoOutput execute(CreateVideoCommand aCommand) {
        final var aRating = Rating.of(aCommand.rating()).orElse(null);
        final var aYear = isNull(aCommand.launchedAt()) ? null : Year.of(aCommand.launchedAt());
        final var categories = toIdentifier(aCommand.categories(), CategoryID::from);
        final var genres = toIdentifier(aCommand.genres(), GenreID::from);
        final var members = toIdentifier(aCommand.members(), CastMemberID::from);

        final var notification = Notification.create();
        notification.append(validateCategories(categories));
        notification.append(validateGenres(genres));
        notification.append(validateMembers(members));

        final var aVideo = Video.newVideo(
                aCommand.title(),
                aCommand.description(),
                aYear,
                aCommand.duration(),
                aRating,
                aCommand.opened(),
                aCommand.published(),
                categories,
                genres,
                members
        );
        aVideo.validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Error", notification);
        }

        return CreateVideoOutput.from(create(aCommand, aVideo));
    }

    private Video create(CreateVideoCommand aCommand, Video aVideo) {
        final var anId = aVideo.getId();
        try {
            final var aVideoMedia = aCommand.getVideo()
                    .map(it -> this.mediaResourceGateway.storeAudioVideo(anId, VideoResource.with(it, VideoMediaType.VIDEO)))
                    .orElse(null);

            final var aTrailerMedia = aCommand.getTrailer()
                    .map(it -> this.mediaResourceGateway.storeAudioVideo(anId, VideoResource.with(it, VideoMediaType.TRAILER)))
                    .orElse(null);

            final var aBannerMedia = aCommand.getBanner()
                    .map(it -> this.mediaResourceGateway.storeImage(anId, VideoResource.with(it, VideoMediaType.BANNER)))
                    .orElse(null);

            final var aThumbMedia = aCommand.getThumbnail()
                    .map(it -> this.mediaResourceGateway.storeImage(anId, VideoResource.with(it, VideoMediaType.THUMBNAIL)))
                    .orElse(null);

            final var aThumbHalfMedia = aCommand.getThumbailHalf()
                    .map(it -> this.mediaResourceGateway.storeImage(anId, VideoResource.with(it, VideoMediaType.THUMBNAIL_HALF)))
                    .orElse(null);

            return this.videoGateway.create(
                    aVideo.setVideo(aVideoMedia)
                            .setBanner(aBannerMedia)
                            .setTrailer(aTrailerMedia)
                            .setThumbnail(aThumbMedia)
                            .setThumbnailHalf(aThumbHalfMedia)
            );
        } catch (final Throwable t) {
            this.mediaResourceGateway.clearResources(anId);
            throw InternalErrorException.with("An error on create video was observed [videoId:%s]".formatted(anId.getValue()), t);
        }
    }

    private ValidationHandler validateCategories(Set<CategoryID> ids) {
        return validateAggregate("categories", ids, categoryGateway::existsByIds);
    }


    private ValidationHandler validateGenres(Set<GenreID> ids) {
        return validateAggregate("genres", ids, genreGateway::existsByIds);
    }


    private ValidationHandler validateMembers(Set<CastMemberID> ids) {
        return validateAggregate("cast members", ids, castMemberGateway::existsByIds);
    }

    private <T extends Identifier> ValidationHandler validateAggregate(
            final String label,
            final Set<T> ids,
            final Function<Iterable<T>, List<T>> existsByIds
    ) {

        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = existsByIds.apply(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds); //this works because we override the equals method on categoryID to use value instead of reference

            final var missingIdsMessage = missingIds.stream()
                    .map(Identifier::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some %s could not be found: %s".formatted(label, missingIdsMessage)));
        }

        return notification;
    }

    private <T> Set<T> toIdentifier(final Set<String> ids, final Function<String, T> mapper) {
        return ids.stream().map(mapper).collect(Collectors.toSet());
    }
}