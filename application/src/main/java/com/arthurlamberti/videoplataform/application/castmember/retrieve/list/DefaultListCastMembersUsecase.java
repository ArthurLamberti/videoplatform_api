package com.arthurlamberti.videoplataform.application.castmember.retrieve.list;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.pagination.SearchQuery;

import static java.util.Objects.requireNonNull;

public non-sealed class DefaultListCastMembersUsecase extends ListCastMembersUsecase {

    private final CastMemberGateway castMemberGateway;

    public DefaultListCastMembersUsecase(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = requireNonNull(castMemberGateway);
    }

    @Override
    public Pagination<ListCastMembersOutput> execute(SearchQuery aQuery) {
        return this.castMemberGateway.findAll(aQuery)
                .map(ListCastMembersOutput::from);
    }
}
