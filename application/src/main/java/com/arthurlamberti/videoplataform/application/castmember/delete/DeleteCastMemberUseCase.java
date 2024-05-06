package com.arthurlamberti.videoplataform.application.castmember.delete;

import com.arthurlamberti.videoplataform.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
extends UnitUseCase<String>
permits DefaultDeleteCastMemberUseCase {
}
