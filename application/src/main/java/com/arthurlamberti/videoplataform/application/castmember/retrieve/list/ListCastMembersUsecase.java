package com.arthurlamberti.videoplataform.application.castmember.retrieve.list;

import com.arthurlamberti.videoplataform.application.UseCase;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUsecase
        extends UseCase<SearchQuery, Pagination<ListCastMembersOutput>>
        permits DefaultListCastMembersUsecase {
}
