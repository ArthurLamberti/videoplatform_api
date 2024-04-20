package com.arthurlamberti.videoplataform.application.category.update;

import com.arthurlamberti.videoplataform.domain.category.Category;

public record UpdateCategoryOutput (
        String id
) {
    public static UpdateCategoryOutput from(final String anId) {
        return new UpdateCategoryOutput(anId);
    }

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }
}
