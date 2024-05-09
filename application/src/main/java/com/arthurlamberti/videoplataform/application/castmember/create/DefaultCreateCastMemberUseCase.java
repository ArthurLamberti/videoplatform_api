package com.arthurlamberti.videoplataform.application.castmember.create;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;

import java.util.Objects;

public non-sealed class DefaultCreateCastMemberUseCase extends CreateCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultCreateCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public CreateCastMemberOutput execute(CreateCastMemberCommand aCommand) {
        final var aName = aCommand.name();
        final var aType = aCommand.type();
        final var notification = Notification.create();
        final var aMember = notification.validate(() -> CastMember.newMember(aName, aType));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate CastMember", notification);
        }

        return CreateCastMemberOutput.from(this.castMemberGateway.create(aMember));
    }
}
