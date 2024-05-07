package com.arthurlamberti.videoplataform.infrastructure.configuration.usecases;

import com.arthurlamberti.videoplataform.application.castmember.create.CreateCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.delete.DeleteCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.retrieve.get.CastMemberByIdUseCase;
import com.arthurlamberti.videoplataform.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import com.arthurlamberti.videoplataform.application.castmember.retrieve.list.DefaultListCastMembersUsecase;
import com.arthurlamberti.videoplataform.application.castmember.retrieve.list.ListCastMembersUsecase;
import com.arthurlamberti.videoplataform.application.castmember.update.DefaultUpdateCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.update.UpdateCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.genre.create.CreateGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.create.DefaultCreateGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.delete.DefaultDeleteGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.delete.DeleteGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.retrieve.get.DefaultGetGenreByIdUseCase;
import com.arthurlamberti.videoplataform.application.genre.retrieve.get.GetGenreByIdUseCase;
import com.arthurlamberti.videoplataform.application.genre.retrieve.list.DefaultListGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.retrieve.list.ListGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.update.DefaultUpdateGenreUseCase;
import com.arthurlamberti.videoplataform.application.genre.update.UpdateGenreUseCase;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.category.CategoryGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CastMemberUseCaseConfig {

    private final CastMemberGateway castMemberGateway;

    public CastMemberUseCaseConfig(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Bean
    public CreateCastMemberUseCase createCastMemberUseCase() {
        return new DefaultCreateCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public DeleteCastMemberUseCase deleteCastMemberUseCase() {
        return new DefaultDeleteCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public CastMemberByIdUseCase getCastMemberByIdUseCase() {
        return new DefaultGetCastMemberByIdUseCase(castMemberGateway);
    }

    @Bean
    public ListCastMembersUsecase listCastMembersUseCase() {
        return new DefaultListCastMembersUsecase(castMemberGateway);
    }

    @Bean
    public UpdateCastMemberUseCase updateCastMemberUseCase() {
        return new DefaultUpdateCastMemberUseCase(castMemberGateway);
    }
}
