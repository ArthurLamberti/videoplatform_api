package com.arthurlamberti.videoplataform.infrastructure.castmember.models;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}