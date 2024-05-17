package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "VideoCategory")
@Table(name = "videos_category")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class VideoCategoryJpaEntity {
    @EmbeddedId
    private VideoCategoryID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private VideoJpaEntity video;

    public static VideoCategoryJpaEntity from(final VideoJpaEntity video, final CategoryID categoryID) {
        return new VideoCategoryJpaEntity(
                VideoCategoryID.from(video.getId(), UUID.fromString(categoryID.getValue())),
                video
        );
    }
}
