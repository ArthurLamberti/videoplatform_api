package com.arthurlamberti.videoplataform.domain.video;

import java.time.Instant;

public record VideoPreview (
        String id,
        String title,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
