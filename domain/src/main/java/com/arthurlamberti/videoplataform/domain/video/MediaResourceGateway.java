package com.arthurlamberti.videoplataform.domain.video;

public interface MediaResourceGateway {
    AudioVideoMedia storeAudioVideo(VideoID anId, Resource resource);

    ImageMedia storeImage(VideoID anId, Resource resource);

    void clearResources(VideoID anId);
}
