package com.arthurlamberti.videoplataform.domain.castmember;

import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CastMemberGateway {

    CastMember create(CastMember aGenre);

    void deleteById(GenreID anId);

    Optional<CastMember> findById(GenreID anId);

    CastMember update(CastMember aGenre);

    Pagination<CastMember> findAll(SearchQuery aQuery);
}
