package com.arthurlamberti.videoplataform.application.castmember.retrieve.get;

import com.arthurlamberti.videoplataform.application.UseCase;

public sealed abstract class CastMemberByIdUseCase
extends UseCase<String, GetCastMemberByIdOutput>
permits DefaultGetCastMemberByIdUseCase {
}
