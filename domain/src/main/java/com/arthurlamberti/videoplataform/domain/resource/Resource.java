package com.arthurlamberti.videoplataform.domain.resource;

import com.arthurlamberti.videoplataform.domain.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class Resource extends ValueObject {

    private final byte[] content;
    private final String contentType;
    private final String name;
    private final String checksum;

    private Resource(final byte[] content, final String contentType, final String name, String checksum) {
        this.content = requireNonNull(content);
        this.contentType = requireNonNull(contentType);
        this.name = requireNonNull(name);
        this.checksum = checksum;
    }

    public static Resource with(final byte[] content, final String contentType, final String name, final String checksum) {
        return new Resource(content, contentType, name, checksum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Arrays.equals(getContent(), resource.getContent()) && Objects.equals(getContentType(), resource.getContentType()) && Objects.equals(getName(), resource.getName()) && Objects.equals(getChecksum(), resource.getChecksum());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getContentType(), getName(), getChecksum());
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }
}
