package com.arthurlamberti.videoplataform.domain.exception;

import com.arthurlamberti.videoplataform.domain.validation.Error;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Getter
public class DomainException extends NoStacktraceException {
    protected final List<Error> errors;

    public DomainException(String message, final List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> errors) {
        return new DomainException("", errors);
    }
}
