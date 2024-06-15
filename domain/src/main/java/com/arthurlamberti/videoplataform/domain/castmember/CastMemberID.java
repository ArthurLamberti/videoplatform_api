package com.arthurlamberti.videoplataform.domain.castmember;

import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.utils.IdUtils;

import java.util.Objects;
import java.util.UUID;

public class CastMemberID extends Identifier {


    private final String value;

    public CastMemberID(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CastMemberID unique() {
        return CastMemberID.from(IdUtils.uuid());
    }

    public static CastMemberID from(final String anId) {
        return new CastMemberID(anId);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CastMemberID that = (CastMemberID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
