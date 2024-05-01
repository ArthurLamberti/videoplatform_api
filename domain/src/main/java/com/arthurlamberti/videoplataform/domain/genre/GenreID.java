package com.arthurlamberti.videoplataform.domain.genre;

import com.arthurlamberti.videoplataform.domain.Identifier;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class GenreID extends Identifier {


    private final String value;

    public GenreID(final String value) {
        requireNonNull(value);
        this.value = value;
    }

    public static GenreID unique() {
        return GenreID.from(UUID.randomUUID());
    }

    private static GenreID from(final UUID randomUUID) {
        return new GenreID(randomUUID.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
