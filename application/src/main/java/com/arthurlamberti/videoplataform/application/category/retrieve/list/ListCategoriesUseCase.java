package com.arthurlamberti.videoplataform.application.category.retrieve.list;

import com.arthurlamberti.videoplataform.application.UseCase;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
