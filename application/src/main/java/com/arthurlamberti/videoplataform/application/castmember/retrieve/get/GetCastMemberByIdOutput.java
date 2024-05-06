package com.arthurlamberti.videoplataform.application.castmember.retrieve.get;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

import java.time.Instant;

public record GetCastMemberByIdOutput (
        String id,
        String name,
        CastMemberType type,
        Instant createdAt,
        Instant updatedAt
){
    public static GetCastMemberByIdOutput from(final CastMember aMember) {
        return new GetCastMemberByIdOutput(
                aMember.getId().getValue(),
                aMember.getName(),
                aMember.getType(),
                aMember.getCreatedAt(),
                aMember.getUpdatedAt()
        );
    }
}
