package com.arthurlamberti.videoplataform.application.genre.retrieve.get;

import com.arthurlamberti.videoplataform.domain.category.CategoryID;

import java.time.Instant;
import java.util.List;

public record GenreOutput(
        String id,
        String name,
        Boolean isActive,
        List<String> categories,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

}
