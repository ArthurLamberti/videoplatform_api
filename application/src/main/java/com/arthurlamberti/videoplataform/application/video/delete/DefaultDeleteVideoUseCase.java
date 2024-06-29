package com.arthurlamberti.videoplataform.application.video.delete;

import com.arthurlamberti.videoplataform.domain.video.MediaResourceGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoID;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteVideoUseCase extends DeleteVideoUseCase {
    private final VideoGateway videoGateway;
    private final MediaResourceGateway mediaResourceGateway;

    public DefaultDeleteVideoUseCase(final VideoGateway videoGateway, final MediaResourceGateway mediaResourceGateway) {
        this.videoGateway = requireNonNull(videoGateway);
        this.mediaResourceGateway = requireNonNull(mediaResourceGateway);
    }

    @Override
    public void execute(final String anId) {
        final VideoID videoId = VideoID.from(anId);
        this.videoGateway.deleteById(VideoID.from(anId));
        this.mediaResourceGateway.clearResources(videoId);
    }
}
