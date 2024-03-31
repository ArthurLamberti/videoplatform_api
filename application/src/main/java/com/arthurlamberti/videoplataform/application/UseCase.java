package com.arthurlamberti.videoplataform.application;

public abstract class UseCase<IN, OUT> {
    public UseCase() {
    }

    public abstract OUT execute(IN var1);
}
