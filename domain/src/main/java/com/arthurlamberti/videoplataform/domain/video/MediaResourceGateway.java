package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.resource.Resource;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.arthurlamberti.videoplataform.domain.resource.VideoResource;

import java.util.Optional;

public interface MediaResourceGateway {
    AudioVideoMedia storeAudioVideo(VideoID anId, VideoResource resource);

    ImageMedia storeImage(VideoID anId, VideoResource resource);

    void clearResources(VideoID anId);

    Optional<Resource> getResource(VideoID anId, VideoMediaType type);
}
