package com.arthurlamberti.videoplataform.domain.genre;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.utils.IdUtils;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class GenreID extends Identifier {


    private final String value;

    public GenreID(final String value) {
        requireNonNull(value);
        this.value = value;
    }

    public static GenreID unique() {
        return GenreID.from(IdUtils.uuid());
    }

    public static GenreID from(final String anId) {
        return new GenreID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GenreID that = (GenreID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
