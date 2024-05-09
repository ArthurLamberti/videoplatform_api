package com.arthurlamberti.videoplataform.application.castmember.retrieve.get;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;

import static java.util.Objects.requireNonNull;

public non-sealed  class DefaultGetCastMemberByIdUseCase extends CastMemberByIdUseCase {
    private final CastMemberGateway castMemberGateway;

    public DefaultGetCastMemberByIdUseCase(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = requireNonNull(castMemberGateway);
    }

    @Override
    public GetCastMemberByIdOutput execute(final String anId) {
        final var aMemberID = CastMemberID.from(anId);
        return this.castMemberGateway.findById(aMemberID)
                .map(GetCastMemberByIdOutput::from)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberID));
    }
}
