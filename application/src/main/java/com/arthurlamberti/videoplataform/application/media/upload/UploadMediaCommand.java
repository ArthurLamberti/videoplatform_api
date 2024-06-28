package com.arthurlamberti.videoplataform.application.media.upload;

import com.arthurlamberti.videoplataform.domain.resource.VideoResource;

public record UploadMediaCommand(
        String videoId,
        VideoResource videoResource
) {

    public static UploadMediaCommand with(final String anId, final VideoResource aResource) {
        return new UploadMediaCommand(anId, aResource);
    }
}
