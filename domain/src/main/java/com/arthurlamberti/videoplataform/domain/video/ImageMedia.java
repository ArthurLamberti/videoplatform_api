package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.ValueObject;
import lombok.Getter;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class ImageMedia extends ValueObject {

    private final String checksum;
    private final String name;
    private final String location;


    private ImageMedia(final String checksum, final String name, final String location) {
        this.checksum = requireNonNull(checksum);
        this.name = requireNonNull(name);
        this.location = requireNonNull(location);
    }

    public static ImageMedia with(final String checksum, final String name, final String location) {
        return new ImageMedia(checksum,name,location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageMedia that = (ImageMedia) o;
        return Objects.equals(getChecksum(), that.getChecksum()) && Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChecksum(), getLocation());
    }
}
