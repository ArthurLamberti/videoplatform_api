package com.arthurlamberti.videoplataform.application.castmember.update;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

public record UpdateCastMemberCommand(
        String id,
        String name,
        CastMemberType type
) {
    public static UpdateCastMemberCommand with(
            final String anId,
            final String anName,
            final CastMemberType aType
    ) {
        return new UpdateCastMemberCommand(anId, anName, aType);
    }

}
