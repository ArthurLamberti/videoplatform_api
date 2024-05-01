package com.arthurlamberti.videoplataform.application.genre.retrieve.list;

import com.arthurlamberti.videoplataform.application.UseCase;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}
