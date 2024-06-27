package com.arthurlamberti.videoplataform.infrastructure.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.arthurlamberti.videoplataform.domain.Fixture;
import com.arthurlamberti.videoplataform.domain.resource.Resource;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
class GCStorageImplTest {
    private GCStorageImpl target;

    private Storage storage;

    private String bucket = "test";

    @BeforeEach
    public void setUp() {
        this.storage = Mockito.mock(Storage.class);
        this.target = new GCStorageImpl(bucket, storage);
    }

    @Test
    public void givenResource_whenCallsGet_shouldRetrieveIt() {
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.THUMBNAIL);
        final var expectedId = expectedResource.getName();

        final Blob blob = mockBlob(expectedResource);
        doReturn(blob).when(storage).get(eq(bucket), eq(expectedId));

        final var actualContent = target.get(expectedId).get();

        Assertions.assertEquals(expectedResource.getChecksum(), actualContent.getChecksum());
        Assertions.assertEquals(expectedResource.getName(), actualContent.getName());
        Assertions.assertEquals(expectedResource.getContent(), actualContent.getContent());
        Assertions.assertEquals(expectedResource.getContentType(), actualContent.getContentType());
    }

    @Test
    public void givenInvalidResource_whenCallsGet_shouldRetrieveEmpty() {
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.THUMBNAIL);
        final var expectedId = expectedResource.getName();

        doReturn(null).when(storage).get(eq(bucket), eq(expectedId));

        final var actualContent = target.get(expectedId);

        Assertions.assertTrue(actualContent.isEmpty());
    }

    @Test
    public void givenPrefix_whenCallsList_shouldRetrieveAll() {
        final var video = Fixture.Videos.resource(VideoMediaType.VIDEO);
        final var banner = Fixture.Videos.resource(VideoMediaType.BANNER);
        final var expectedIds = List.of(video.getName(), banner.getName());

        final var page = Mockito.mock(Page.class);

        final Blob blob1 = mockBlob(video);
        final Blob blob2 = mockBlob(banner);

        doReturn(List.of(blob1, blob2)).when(page).iterateAll();
        doReturn(page).when(storage).list(eq(bucket), eq(Storage.BlobListOption.prefix("it")));

        final var actualContent = target.list("it");

        Assertions.assertTrue(
                expectedIds.size() == actualContent.size()
                        && expectedIds.containsAll(actualContent)
        );
    }

    @Test
    public void givenResource_whenCallsDeleteAll_shouldEmptyStorage() {
        final var expectedIds = List.of("item1", "item2");

        target.deleteAll(expectedIds);

        final var capturer = ArgumentCaptor.forClass(List.class);

        verify(storage, times(1)).delete(capturer.capture());

        final var actualIds = ((List<BlobId>) capturer.getValue()).stream()
                .map(BlobId::getName)
                .toList();

        Assertions.assertTrue(expectedIds.size() == actualIds.size() && actualIds.containsAll(expectedIds));
    }

    private Blob mockBlob(final Resource resource) {
        final var blob1 = Mockito.mock(Blob.class);
        when(blob1.getBlobId()).thenReturn(BlobId.of(bucket, resource.getName()));
        when(blob1.getCrc32cToHexString()).thenReturn(resource.getChecksum());
        when(blob1.getContent()).thenReturn(resource.getContent());
        when(blob1.getContentType()).thenReturn(resource.getContentType());
        when(blob1.getName()).thenReturn(resource.getName());
        return blob1;
    }
}