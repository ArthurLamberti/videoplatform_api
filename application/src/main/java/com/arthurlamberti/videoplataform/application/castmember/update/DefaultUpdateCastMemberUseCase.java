package com.arthurlamberti.videoplataform.application.castmember.update;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;

import static java.util.Objects.requireNonNull;

public final class DefaultUpdateCastMemberUseCase extends UpdateCastMemberUseCase {

    private final CastMemberGateway castMemberGatewayl;

    public DefaultUpdateCastMemberUseCase(CastMemberGateway castMemberGatewayl) {
        this.castMemberGatewayl = requireNonNull(castMemberGatewayl);
    }

    @Override
    public UpdateCastMemberOutput execute(UpdateCastMemberCommand aCommand) {
        final var anId = CastMemberID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aType = aCommand.type();

        final var member = this.castMemberGatewayl.findById(anId)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, anId));

        final var notification = Notification.create();
        notification.validate(() -> member.update(aName, aType));

        if (notification.hasError()) {
            throw new NotificationException("Could not update Aggregate CastMember %s".formatted(anId.getValue()), notification);
        }

        return UpdateCastMemberOutput.from(this.castMemberGatewayl.update(member));
    }
}
