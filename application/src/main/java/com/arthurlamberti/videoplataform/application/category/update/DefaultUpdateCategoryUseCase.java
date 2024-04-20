package com.arthurlamberti.videoplataform.application.category.update;

import com.arthurlamberti.videoplataform.domain.category.Category;
import com.arthurlamberti.videoplataform.domain.category.CategoryGateway;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.exception.DomainException;
import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public UpdateCategoryOutput execute(final UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = this.categoryGateway.findById(anId)
                .orElseThrow(notFound(anId));

        aCategory.update(aName, aDescription, isActive);
        aCategory.validate(notification);

        return UpdateCategoryOutput.from(this.categoryGateway.update(aCategory));
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
