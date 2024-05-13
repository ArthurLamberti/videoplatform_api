package com.arthurlamberti.videoplataform.domain.exception;

import com.arthurlamberti.videoplataform.domain.validation.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class InternalErrorException extends NoStacktraceException {

    public InternalErrorException(String message, final Throwable t) {
        super(message, t);
    }

    public static InternalErrorException with(final String message, final Throwable t) {
        return new InternalErrorException(message, t);
    }
}
