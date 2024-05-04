package com.arthurlamberti.videoplataform.application.castmember.update;

import com.arthurlamberti.videoplataform.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
        extends UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput>
        permits DefaultUpdateCastMemberUseCase {
}
