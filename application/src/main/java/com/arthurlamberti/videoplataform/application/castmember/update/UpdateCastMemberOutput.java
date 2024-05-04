package com.arthurlamberti.videoplataform.application.castmember.update;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

public record UpdateCastMemberOutput(
        String id
) {
    public static UpdateCastMemberOutput from(
            final CastMember castMember
    ) {
        return new UpdateCastMemberOutput(castMember.getId().getValue());
    }

}
