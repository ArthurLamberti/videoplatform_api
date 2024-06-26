package com.arthurlamberti.videoplataform.infrastructure.api.controller;

import com.arthurlamberti.videoplataform.application.category.create.CreateCategoryCommand;
import com.arthurlamberti.videoplataform.application.category.create.CreateCategoryOutput;
import com.arthurlamberti.videoplataform.application.category.create.CreateCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.delete.DeleteCategoryUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.arthurlamberti.videoplataform.application.category.retrieve.list.ListCategoriesUseCase;
import com.arthurlamberti.videoplataform.application.category.update.UpdateCategoryCommand;
import com.arthurlamberti.videoplataform.application.category.update.UpdateCategoryOutput;
import com.arthurlamberti.videoplataform.application.category.update.UpdateCategoryUseCase;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;
import com.arthurlamberti.videoplataform.infrastructure.api.CategoryAPI;
import com.arthurlamberti.videoplataform.infrastructure.category.models.CategoryListResponse;
import com.arthurlamberti.videoplataform.infrastructure.category.models.CategoryResponse;
import com.arthurlamberti.videoplataform.infrastructure.category.models.CreateCategoryRequest;
import com.arthurlamberti.videoplataform.infrastructure.category.models.UpdateCategoryRequest;
import com.arthurlamberti.videoplataform.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final ListCategoriesUseCase listCategoriesUseCase
    ) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoriesUseCase = Objects.requireNonNull(listCategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        CreateCategoryOutput output = this.createCategoryUseCase.execute(aCommand);


        return ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);
    }

    @Override
    public Pagination<CategoryListResponse> listCategories(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listCategoriesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CategoryApiPresenter::present);
    }

    @Override
    public CategoryResponse getById(final String id) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        return ResponseEntity.ok(this.updateCategoryUseCase.execute(aCommand));
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteCategoryUseCase.execute(anId);
    }
}
