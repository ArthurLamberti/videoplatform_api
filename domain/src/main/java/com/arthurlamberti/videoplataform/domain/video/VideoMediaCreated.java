package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.events.DomainEvent;
import com.arthurlamberti.videoplataform.domain.utils.InstantUtils;

import java.time.Instant;

public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn
) implements DomainEvent {

    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}
