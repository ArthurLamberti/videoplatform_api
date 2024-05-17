package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
public class VideoCategoryID implements Serializable {

    @Column(name = "video_id", nullable = false)
    private UUID videoId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    public static VideoCategoryID from(final UUID videoId, final UUID categoryId) {
        return new VideoCategoryID(videoId, categoryId);
    }
}
