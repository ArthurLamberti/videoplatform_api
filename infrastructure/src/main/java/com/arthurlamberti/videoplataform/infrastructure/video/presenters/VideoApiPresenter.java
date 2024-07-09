package com.arthurlamberti.videoplataform.infrastructure.video.presenters;

import com.arthurlamberti.videoplataform.application.media.upload.UploadMediaOutput;
import com.arthurlamberti.videoplataform.application.video.retrieve.get.VideoOutput;
import com.arthurlamberti.videoplataform.application.video.retrieve.list.VideoListOutput;
import com.arthurlamberti.videoplataform.application.video.update.UpdateVideoOutput;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.video.AudioVideoMedia;
import com.arthurlamberti.videoplataform.domain.video.ImageMedia;
import com.arthurlamberti.videoplataform.infrastructure.video.models.*;

public interface VideoApiPresenter {

    static VideoResponse present(final VideoOutput output) {
        return new VideoResponse(
                output.id(),
                output.title(),
                output.description(),
                output.launchedAt(),
                output.duration(),
                output.opened(),
                output.published(),
                output.rating().getName(),
                output.createdAt(),
                output.updatedAt(),
                present(output.banner()),
                present(output.thumbnail()),
                present(output.thumbnailHalf()),
                present(output.video()),
                present(output.trailer()),
                output.categories(),
                output.genres(),
                output.castMembers()
        );
    }

    static AudioVideoMediaResponse present(final AudioVideoMedia media) {
        if (media == null) {
            return null;
        }
        return new AudioVideoMediaResponse(
                media.getId(),
                media.getChecksum(),
                media.getName(),
                media.getRawLocation(),
                media.getEncodedLocation(),
                media.getStatus().name()
        );
    }

    static ImageMediaResponse present(final ImageMedia image) {
        if (image == null) {
            return null;
        }
        return new ImageMediaResponse(
                image.getId(),
                image.getChecksum(),
                image.getName(),
                image.getLocation()
        );
    }

    static UpdateVideoResponse present(final UpdateVideoOutput output) {
        return new UpdateVideoResponse(output.id());
    }

    static VideoListResponse present(final VideoListOutput output) {
        return new VideoListResponse(
                output.id(),
                output.title(),
                output.description(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static Pagination<VideoListResponse> present(final Pagination<VideoListOutput> page) {
        return page.map(VideoApiPresenter::present);
    }

    static UploadMediaResponse present(final UploadMediaOutput output) {
        return new UploadMediaResponse(output.videoId(), output.mediaType());
    }
}