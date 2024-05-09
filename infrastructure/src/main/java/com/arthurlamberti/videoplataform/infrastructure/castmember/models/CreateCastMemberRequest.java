package com.arthurlamberti.videoplataform.infrastructure.castmember.models;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}