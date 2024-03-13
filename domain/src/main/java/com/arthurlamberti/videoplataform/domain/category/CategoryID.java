package com.arthurlamberti.videoplataform.domain.category;

import com.arthurlamberti.videoplataform.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CategoryID unique() {
        return CategoryID.from(UUID.randomUUID());
    }

    public static CategoryID from(final String anId) {
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID uuid) {
        return new CategoryID(uuid.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }
}
