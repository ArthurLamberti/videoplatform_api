package com.arthurlamberti.videoplataform.infrastructure.castmember.presenter;

import com.arthurlamberti.videoplataform.application.castmember.retrieve.get.GetCastMemberByIdOutput;
import com.arthurlamberti.videoplataform.application.castmember.retrieve.list.ListCastMembersOutput;
import com.arthurlamberti.videoplataform.infrastructure.castmember.models.CastMemberListResponse;
import com.arthurlamberti.videoplataform.infrastructure.castmember.models.CastMemberResponse;

public interface CastMemberPresenter {

    static CastMemberResponse present(final GetCastMemberByIdOutput aMember) {
        return new CastMemberResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString(),
                aMember.updatedAt().toString()
        );
    }

    static CastMemberListResponse present(final ListCastMembersOutput aMember) {
        return new CastMemberListResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString()
        );
    }
}
