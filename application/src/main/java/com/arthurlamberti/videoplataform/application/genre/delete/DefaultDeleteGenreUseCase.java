package com.arthurlamberti.videoplataform.application.genre.delete;

import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteGenreUseCase extends DeleteGenreUseCase {


    private final GenreGateway genreGateway;

    public DefaultDeleteGenreUseCase(final GenreGateway genreGateway) {
        this.genreGateway = requireNonNull(genreGateway);
    }

    @Override
    public void execute(final String anId) {
        this.genreGateway.deleteById(GenreID.from(anId));
    }
}
