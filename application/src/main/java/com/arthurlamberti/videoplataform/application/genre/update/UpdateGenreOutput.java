package com.arthurlamberti.videoplataform.application.genre.update;

import com.arthurlamberti.videoplataform.domain.genre.Genre;

public record UpdateGenreOutput(String id) {

    public static UpdateGenreOutput from(Genre aGenre) {
        return new UpdateGenreOutput(aGenre.getId().getValue());
    }
}
