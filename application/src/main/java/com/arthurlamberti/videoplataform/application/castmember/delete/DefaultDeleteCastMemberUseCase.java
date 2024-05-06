package com.arthurlamberti.videoplataform.application.castmember.delete;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;

import static java.util.Objects.requireNonNull;

public final class DefaultDeleteCastMemberUseCase extends DeleteCastMemberUseCase
{
    private CastMemberGateway castMemberGateway;

    public DefaultDeleteCastMemberUseCase(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = requireNonNull(castMemberGateway);
    }

    @Override
    public void execute(String anId) {
        this.castMemberGateway.deleteById(CastMemberID.from(anId));
    }
}
