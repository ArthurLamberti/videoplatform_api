package com.arthurlamberti.videoplataform.infrastructure.video;

import com.arthurlamberti.videoplataform.domain.resource.Resource;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.arthurlamberti.videoplataform.domain.resource.VideoResource;
import com.arthurlamberti.videoplataform.domain.video.AudioVideoMedia;
import com.arthurlamberti.videoplataform.domain.video.ImageMedia;
import com.arthurlamberti.videoplataform.domain.video.MediaResourceGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoID;
import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.storage.StorageProperties;
import com.arthurlamberti.videoplataform.infrastructure.services.StorageService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultMediaResourceGateway implements MediaResourceGateway {

    private final String filenamePattern;
    private final String locationPattern;
    private final StorageService storageService;

    public DefaultMediaResourceGateway(final StorageProperties props, StorageService storageService) {
        this.filenamePattern = props.getFilenamePattern();
        this.locationPattern = props.getLocationPattern();
        this.storageService = storageService;
    }

    @Override
    public AudioVideoMedia storeAudioVideo(VideoID anId, VideoResource videoResource) {
        final var filepath = filepath(anId, videoResource.getType());
        final var resource = videoResource.getResource();
        store(filepath, resource);

        return AudioVideoMedia.with(resource.getChecksum(), resource.getName(), filepath);
    }


    @Override
    public ImageMedia storeImage(VideoID anId, VideoResource videoResource) {
        final var filepath = filepath(anId, videoResource.getType());
        final var resource = videoResource.getResource();
        store(filepath, resource);

        return ImageMedia.with(resource.getChecksum(), resource.getName(), filepath);
    }

    @Override
    public void clearResources(VideoID anId) {
        final var ids = this.storageService.list(folder(anId));
        this.storageService.deleteAll(ids);
    }

    @Override
    public Optional<Resource> getResource(VideoID anId, VideoMediaType type) {
        return this.storageService.get(filepath(anId, type));
    }

    private String filename(final VideoMediaType aType) {
        return filenamePattern.replace("{type}", aType.name());
    }

    private String folder(final VideoID anId) {
        return locationPattern.replace("videoId", anId.getValue());
    }

    private String filepath(VideoID anId, VideoMediaType aType) {
        return folder(anId).concat("/").concat(filename(aType));
    }

    private void store(String filepath, Resource resource) {
        this.storageService.store(filepath, resource);
    }
}
