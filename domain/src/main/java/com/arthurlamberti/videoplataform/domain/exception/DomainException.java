package com.arthurlamberti.videoplataform.domain.exception;

import com.arthurlamberti.videoplataform.domain.validation.Error;

import java.util.Collection;

public class DomainException extends NoStacktraceException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public Collection<Error> getErrors() {
        return null;
    }
}
