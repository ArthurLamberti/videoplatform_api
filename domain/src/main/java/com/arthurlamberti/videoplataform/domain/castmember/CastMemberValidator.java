package com.arthurlamberti.videoplataform.domain.castmember;

import com.arthurlamberti.videoplataform.domain.validation.Error;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import com.arthurlamberti.videoplataform.domain.validation.Validator;

public class CastMemberValidator extends Validator {

    private final CastMember castMember;

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 1;

    public CastMemberValidator(final ValidationHandler aHandler, final CastMember castMember) {
        super(aHandler);
        this.castMember = castMember;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkTypeConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.castMember.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 1 and 255 characters"));
        }
    }
    private void checkTypeConstraints() {
        final var type = this.castMember.getType();
        if (type == null) {
            this.validationHandler().append(new Error("'type' should not be null"));
        }
    }
}
