package com.arthurlamberti.videoplataform.domain.castmember;

import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.utils.InstantUtils;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class CastMember extends AggregateRoot<CastMemberID> {

    private String name;
    private CastMemberType type;
    private Instant createdAt;
    private Instant updatedAt;

    protected CastMember(
            final CastMemberID castMemberID,
            final String aName,
            final CastMemberType type,
            final Instant aCreatedAt,
            final Instant anUpdatedAt
    ) {
        super(castMemberID);
        this.name = aName;
        this.type = type;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        selfValidate();
    }

    public static CastMember newMember(final String aName, final CastMemberType aType) {
        final var anId = CastMemberID.unique();
        final var now = InstantUtils.now();
        return new CastMember(anId, aName, aType, now, now);
    }


    public static CastMember with(CastMember aGenre) {
        return new CastMember(
                aGenre.id,
                aGenre.name,
                aGenre.type,
                aGenre.createdAt,
                aGenre.updatedAt
        );
    }

    public static CastMember with(
            final CastMemberID anId,
            final String aName,
            final CastMemberType aType,
            final Instant aCreatedAt,
            final Instant aUpdatedAt
    ) {
        return new CastMember(anId, aName, aType, aCreatedAt, aUpdatedAt);
    }

    public CastMember update(final String aName, final CastMemberType aType) {
        this.name = aName;
        this.type = aType;
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError()) {
            throw new NotificationException("Failed to creat a Aggregate CastMember", notification);
        }
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CastMemberValidator(handler, this).validate();
    }
}
