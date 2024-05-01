package com.arthurlamberti.videoplataform.application.castmember.create;

import com.arthurlamberti.videoplataform.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
        extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
        permits DefaultCreateCastMemberUseCase {
}
