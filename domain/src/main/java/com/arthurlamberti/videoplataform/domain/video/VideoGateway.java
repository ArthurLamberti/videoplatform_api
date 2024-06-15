package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.pagination.Pagination;

import java.util.Optional;

public interface VideoGateway {
    Video create(Video aVideo);

    void deleteById(VideoID anId);

    Optional<Video> findById(VideoID anId);

    Video update(Video aGenre);

    Pagination<VideoPreview> findAll(VideoSearchQuery aQuery);
}
