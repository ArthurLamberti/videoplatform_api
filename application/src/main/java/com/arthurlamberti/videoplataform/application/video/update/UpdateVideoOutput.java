package com.arthurlamberti.videoplataform.application.video.update;

import com.arthurlamberti.videoplataform.domain.video.Video;

public record UpdateVideoOutput(String id){
    public static UpdateVideoOutput from(final Video aVideo){
        return new UpdateVideoOutput(aVideo.getId().getValue());
    }
}
