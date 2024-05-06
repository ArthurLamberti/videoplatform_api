package com.arthurlamberti.videoplataform.application.castmember.retrieve.list;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

import java.time.Instant;

public record ListCastMembersOutput(
        String id,
        String name,
        CastMemberType type,
        Instant createdAt
) {
    public static ListCastMembersOutput from(final CastMember castMember) {
        return new ListCastMembersOutput(
                castMember.getId().getValue(),
                castMember.getName(),
                castMember.getType(),
                castMember.getCreatedAt()
        );
    }

}
