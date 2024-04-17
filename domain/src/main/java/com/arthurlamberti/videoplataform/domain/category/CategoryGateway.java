package com.arthurlamberti.videoplataform.domain.category;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);

}
