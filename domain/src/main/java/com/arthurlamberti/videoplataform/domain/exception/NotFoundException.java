package com.arthurlamberti.videoplataform.domain.exception;


import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.Identifier;
import com.arthurlamberti.videoplataform.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, List.of(new Error(anError)));
    }
}
