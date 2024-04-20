package com.arthurlamberti.videoplataform.domain.category;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);
    Optional<Category> findById(CategoryID anId);
    Category update(Category aCategory);

}
