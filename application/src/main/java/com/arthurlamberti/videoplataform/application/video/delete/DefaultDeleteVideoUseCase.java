package com.arthurlamberti.videoplataform.application.video.delete;

import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoID;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteVideoUseCase extends DeleteVideoUseCase {
    private final VideoGateway videoGateway;

    public DefaultDeleteVideoUseCase(final VideoGateway videoGateway) {
        this.videoGateway = requireNonNull(videoGateway);
    }

    @Override
    public void execute(final String anId) {
        this.videoGateway.deleteById(VideoID.from(anId));
    }
}
