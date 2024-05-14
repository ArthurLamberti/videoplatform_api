package com.arthurlamberti.videoplataform.application.video.retrieve.get;

import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.video.Video;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoID;

import static java.util.Objects.requireNonNull;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase{
    private final VideoGateway videoGateway;

    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = requireNonNull(videoGateway);
    }

    @Override
    public VideoOutput execute(String anId) {
        VideoID videoID = VideoID.from(anId);
        return this.videoGateway.findById(videoID)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, videoID));
    }
}
