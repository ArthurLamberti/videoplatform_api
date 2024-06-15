package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.utils.IdUtils;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class VideoID extends Identifier {
    private final String value;

    private VideoID(final String value) {
        this.value = requireNonNull(value);
    }

    public static VideoID unique() {
        return VideoID.from(IdUtils.uuid());
    }

    public static VideoID from(final String anId) {
        return new VideoID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final VideoID that = (VideoID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
