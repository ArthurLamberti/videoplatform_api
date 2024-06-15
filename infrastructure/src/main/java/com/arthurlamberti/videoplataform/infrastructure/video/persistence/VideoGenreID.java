package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class VideoGenreID implements Serializable {

    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "genre_id", nullable = false)
    private String genreId;

    public static VideoGenreID from (final String videoId, final String genreId){
        return new VideoGenreID(videoId, genreId);
    }
}
