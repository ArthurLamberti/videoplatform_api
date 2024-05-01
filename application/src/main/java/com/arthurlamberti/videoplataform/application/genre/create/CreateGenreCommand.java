package com.arthurlamberti.videoplataform.application.genre.create;

import java.util.List;

import static java.util.Objects.isNull;

public record CreateGenreCommand(
        String name,
        boolean isActive,
        List<String> categories
) {

    public static CreateGenreCommand with(final String aName, final Boolean isActive, final List<String> categories) {
        return new CreateGenreCommand(aName, isNull(isActive) || isActive, categories);
    }
}
