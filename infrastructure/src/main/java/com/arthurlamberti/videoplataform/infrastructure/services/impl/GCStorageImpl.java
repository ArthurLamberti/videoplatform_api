package com.arthurlamberti.videoplataform.infrastructure.services.impl;

import com.arthurlamberti.videoplataform.domain.video.Resource;
import com.arthurlamberti.videoplataform.infrastructure.services.StorageService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class GCStorageImpl implements StorageService {
    private final String bucket;
    private final Storage storage;

    public GCStorageImpl(final String bucket, final Storage storage) {
        this.bucket = bucket;
        this.storage = storage;
    }

    @Override
    public void store(final String name, final Resource resource) {
        final var info = BlobInfo.newBuilder(this.bucket, name)
                .setContentDisposition(resource.getContentType())
                .setCrc32cFromHexString("")
                .build();
        this.storage.create(info, resource.getContent());
    }

    @Override
    public Optional<Resource> get(String name) {
        return Optional.ofNullable(this.storage.get(this.bucket, name))
                .map(blob -> Resource.with(
                        blob.getContent(),
                        blob.getContentType(),
                        name,
                        null
                ));
    }

    @Override
    public void deleteAll(final Collection<String> names) {
        final var blobs = names.stream()
                .map(name -> BlobId.of(this.bucket, name))
                .toList();
        this.storage.delete(blobs);
    }

    @Override
    public List<String> list(String prefix) {
        final var blobs = this.storage.list(this.bucket, Storage.BlobListOption.prefix(prefix));
        return StreamSupport.stream(blobs.iterateAll().spliterator(), false)
                .map(BlobInfo::getBlobId)
                .map(BlobId::getName)
                .toList();
    }
}
