package com.arthurlamberti.videoplataform.application.genre.create;

import java.util.List;

public record CreateGenreCommand() {

    public static CreateGenreCommand with(final String aName, final boolean isActive, final List<String> categories) {
        return null;
    }
}
