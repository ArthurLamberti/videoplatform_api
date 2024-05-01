package com.arthurlamberti.videoplataform.application.genre.update;

import java.util.List;

public record UpdateGenreCommand(
) {

    public static UpdateGenreCommand with(
            final String id,
            final String name,
            final boolean isActive,
            final List<String> categories) {
        return null;
    }
}
