package com.arthurlamberti.videoplataform.application.video.retrieve.get;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.video.AudioVideoMedia;
import com.arthurlamberti.videoplataform.domain.video.ImageMedia;
import com.arthurlamberti.videoplataform.domain.video.Video;

import java.time.Instant;
import java.util.Set;

import static com.arthurlamberti.videoplataform.domain.utils.CollectionsUtils.mapTo;

public record VideoOutput(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String title,
        String description,
        int launchedAt,
        double duration,
        boolean opened,
        boolean published,
        String rating,
        Set<String> categories,
        Set<String> genres,
        Set<String> castMembers,
        ImageMedia banner,
        ImageMedia thumbnail,
        ImageMedia thumbnailHalf,
        AudioVideoMedia video,
        AudioVideoMedia trailer
) {
    public static VideoOutput from (final Video aVideo) {
        return new VideoOutput(
                aVideo.getId().getValue(),
                aVideo.getCreatedAt(),
                aVideo.getUpdatedAt(),
                aVideo.getTitle(),
                aVideo.getDescription(),
                aVideo.getLaunchedAt().getValue(),
                aVideo.getDuration(),
                aVideo.isOpened(),
                aVideo.isPublished(),
                aVideo.getRating().getName(),
                mapTo(aVideo.getCategories(), Identifier::getValue),
                mapTo(aVideo.getGenres(), Identifier::getValue),
                mapTo(aVideo.getCastMembers(), Identifier::getValue),
                aVideo.getBanner().orElse(null),
                aVideo.getThumbnail().orElse(null),
                aVideo.getThumbnailHalf().orElse(null),
                aVideo.getVideo().orElse(null),
                aVideo.getTrailer().orElse(null)
        );
    }
}
