package com.arthurlamberti.videoplataform.infrastructure.configuration.usecases;

import com.arthurlamberti.videoplataform.application.media.get.DefaultGetMediaUseCase;
import com.arthurlamberti.videoplataform.application.media.get.GetMediaUseCase;
import com.arthurlamberti.videoplataform.application.media.update.DefaultUpdateMediaStatusUseCase;
import com.arthurlamberti.videoplataform.application.media.update.UpdateMediaStatusUseCase;
import com.arthurlamberti.videoplataform.application.media.upload.DefaultUploadMediaUseCase;
import com.arthurlamberti.videoplataform.application.media.upload.UploadMediaUseCase;
import com.arthurlamberti.videoplataform.application.video.create.CreateVideoUseCase;
import com.arthurlamberti.videoplataform.application.video.create.DefaultCreateVideoUseCase;
import com.arthurlamberti.videoplataform.application.video.delete.DefaultDeleteVideoUseCase;
import com.arthurlamberti.videoplataform.application.video.delete.DeleteVideoUseCase;
import com.arthurlamberti.videoplataform.application.video.retrieve.get.DefaultGetVideoByIdUseCase;
import com.arthurlamberti.videoplataform.application.video.retrieve.get.GetVideoByIdUseCase;
import com.arthurlamberti.videoplataform.application.video.retrieve.list.DefaultListVideosUseCase;
import com.arthurlamberti.videoplataform.application.video.retrieve.list.ListVideosUseCase;
import com.arthurlamberti.videoplataform.application.video.update.DefaultUpdateVideoUseCase;
import com.arthurlamberti.videoplataform.application.video.update.UpdateVideoUseCase;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.category.CategoryGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import com.arthurlamberti.videoplataform.domain.video.MediaResourceGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class VideoUseCaseConfig {
    private final CategoryGateway categoryGateway;
    private final CastMemberGateway castMemberGateway;
    private final GenreGateway genreGateway;
    private final MediaResourceGateway mediaResourceGateway;
    private final VideoGateway videoGateway;

    public VideoUseCaseConfig(
            final CategoryGateway categoryGateway,
            final CastMemberGateway castMemberGateway,
            final GenreGateway genreGateway,
            final MediaResourceGateway mediaResourceGateway,
            final VideoGateway videoGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Bean
    public CreateVideoUseCase createVideoUseCase() {
        return new DefaultCreateVideoUseCase(videoGateway, categoryGateway, genreGateway, castMemberGateway, mediaResourceGateway);
    }

    @Bean
    public UpdateVideoUseCase updateVideoUseCase() {
        return new DefaultUpdateVideoUseCase(videoGateway, categoryGateway, genreGateway, castMemberGateway, mediaResourceGateway);
    }

    @Bean
    public GetVideoByIdUseCase getVideoByIdUseCase() {
        return new DefaultGetVideoByIdUseCase(videoGateway);
    }

    @Bean
    public DeleteVideoUseCase deleteVideoUseCase() {
        return new DefaultDeleteVideoUseCase(videoGateway, mediaResourceGateway);
    }

    @Bean
    public ListVideosUseCase listVideosUseCase() {
        return new DefaultListVideosUseCase(videoGateway);
    }

    @Bean
    public GetMediaUseCase getMediaUseCase() {
        return new DefaultGetMediaUseCase(mediaResourceGateway);
    }

    @Bean
    public UploadMediaUseCase uploadMediaUseCase() {
        return new DefaultUploadMediaUseCase(mediaResourceGateway, videoGateway);
    }

    @Bean
    public UpdateMediaStatusUseCase updateMediaStatusUseCase() {
        return new DefaultUpdateMediaStatusUseCase(videoGateway);
    }
}
