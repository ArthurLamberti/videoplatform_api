package com.arthurlamberti.videoplataform.application.genre.retrieve.get;

import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.genre.Genre;
import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;

import static java.util.Objects.requireNonNull;

public class DefaultGetGenreByIdUseCase extends GetGenreByIdUseCase {

    private final GenreGateway genreGateway;

    public DefaultGetGenreByIdUseCase(final GenreGateway genreGateway) {
        this.genreGateway = requireNonNull(genreGateway);
    }

    @Override
    public GenreOutput execute(final String anId) {
        final var aGenreID = GenreID.from(anId);
        return this.genreGateway.findById(aGenreID)
                .map(GenreOutput::from)
                .orElseThrow(()-> NotFoundException.with(Genre.class, aGenreID));
    }
}
