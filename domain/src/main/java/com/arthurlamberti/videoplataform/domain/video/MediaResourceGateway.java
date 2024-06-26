package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.resource.Resource;
import com.arthurlamberti.videoplataform.domain.resource.VideoResource;

public interface MediaResourceGateway {
    AudioVideoMedia storeAudioVideo(VideoID anId, VideoResource resource);

    ImageMedia storeImage(VideoID anId, VideoResource resource);

    void clearResources(VideoID anId);
}
