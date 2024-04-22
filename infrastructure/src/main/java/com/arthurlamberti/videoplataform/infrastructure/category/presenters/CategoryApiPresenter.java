package com.arthurlamberti.videoplataform.infrastructure.category.presenters;


import com.arthurlamberti.videoplataform.application.category.retrieve.get.CategoryOutput;
import com.arthurlamberti.videoplataform.application.category.retrieve.list.CategoryListOutput;
import com.arthurlamberti.videoplataform.infrastructure.category.models.CategoryListResponse;
import com.arthurlamberti.videoplataform.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
