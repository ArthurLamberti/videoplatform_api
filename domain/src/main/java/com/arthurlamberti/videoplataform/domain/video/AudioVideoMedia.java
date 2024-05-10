package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.ValueObject;
import lombok.Getter;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class AudioVideoMedia extends ValueObject {

    private final String checksum;
    private final String name;
    private final String rawLocation;
    private final String encodedLocation;
    private final MediaStatus status;


    private AudioVideoMedia(final String checksum,
                            final String name,
                            final String rawLocation,
                            final String encodedLocation,
                            final MediaStatus status) {
        this.checksum = requireNonNull(checksum);
        this.name = requireNonNull(name);
        this.rawLocation = requireNonNull(rawLocation);
        this.encodedLocation = requireNonNull(encodedLocation);
        this.status = requireNonNull(status);
    }

    public static AudioVideoMedia with(
            final String checksum,
            final String name,
            final String rawLocation,
            final String encodedLocation,
            final MediaStatus status
    ) {
        return new AudioVideoMedia(checksum,name,rawLocation,encodedLocation,status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AudioVideoMedia that = (AudioVideoMedia) o;
        return Objects.equals(getChecksum(), that.getChecksum()) && Objects.equals(getRawLocation(), that.getRawLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChecksum(), getRawLocation());
    }
}
