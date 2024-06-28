package com.arthurlamberti.videoplataform.application.media.get;

import com.arthurlamberti.videoplataform.domain.resource.Resource;

public record MediaOutput(
        byte[] content,
        String contentType,
        String name
) {
    public static MediaOutput with(final Resource aResource) {
        return new MediaOutput(
                aResource.getContent(),
                aResource.getContentType(),
                aResource.getName()
        );
    }
}
