package com.arthurlamberti.videoplataform.application.castmember.create;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

public record CreateCastMemberCommand(
        String name,
        CastMemberType type
) {

    public static CreateCastMemberCommand with(final String aName, final CastMemberType castMemberType) {
        return new CreateCastMemberCommand(aName, castMemberType);
    }
}
