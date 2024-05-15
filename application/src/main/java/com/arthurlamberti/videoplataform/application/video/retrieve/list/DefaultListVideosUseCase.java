package com.arthurlamberti.videoplataform.application.video.retrieve.list;

import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoSearchQuery;

import static java.util.Objects.requireNonNull;

public class DefaultListVideosUseCase extends ListVideosUseCase{

    private final VideoGateway videoGateway;

    public DefaultListVideosUseCase(final VideoGateway videoGateway) {
        this.videoGateway = requireNonNull(videoGateway);
    }
    @Override
    public Pagination<VideoListOutput> execute(VideoSearchQuery aQuery) {
        return this.videoGateway.findAll(aQuery)
                .map(VideoListOutput::from);
    }
}
