package com.arthurlamberti.videoplataform.application.video.create;

import com.arthurlamberti.videoplataform.domain.video.Video;

public record CreateVideoOutput(String id) {
    public static CreateVideoOutput from(final Video video) {
        return new CreateVideoOutput(video.getId().getValue());
    }
}
