package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "VideoGenre")
@Table(name = "videos_genres")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
public class VideoGenreJpaEntity {
    @EmbeddedId
    private VideoGenreID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private VideoJpaEntity video;

    public static VideoGenreJpaEntity from(final VideoJpaEntity video, final GenreID genre) {
        return new VideoGenreJpaEntity(
                VideoGenreID.from(video.getId(), genre.getValue()),
                video
        );
    }
}
