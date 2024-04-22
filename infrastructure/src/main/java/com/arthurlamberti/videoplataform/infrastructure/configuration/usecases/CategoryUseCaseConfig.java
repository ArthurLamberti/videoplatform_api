package com.arthurlamberti.videoplataform.infrastructure.configuration.usecases;


import com.arthurlamberti.videoplataform.application.category.create.CreateCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.create.DefaultCreateCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.delete.DefaultDeleteCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.delete.DeleteCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.list.ListCategoriesUseCase;
import com.arthurlamberti.videoplataform.application.category.update.DefaultUpdateCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.update.UpdateCategoryUseCase;
import com.arthurlamberti.videoplataform.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
}
