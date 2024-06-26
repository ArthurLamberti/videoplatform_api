package com.arthurlamberti.videoplataform.domain.resource;

import com.arthurlamberti.videoplataform.domain.ValueObject;
import lombok.Getter;

import java.util.Objects;

@Getter
public class VideoResource extends ValueObject {
    private final VideoMediaType type;
    private final Resource resource;


    public VideoResource(VideoMediaType type, Resource resource) {
        this.type = Objects.requireNonNull(type);
        this.resource = Objects.requireNonNull(resource);
    }

    public static VideoResource with(final Resource resource, final VideoMediaType aType) {
        return new VideoResource(aType, resource);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoResource that = (VideoResource) o;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }
}
