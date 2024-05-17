package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.video.ImageMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "videos_image_media")
@Entity(name = "ImageMedia")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageMediaJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    public static ImageMediaJpaEntity from(final ImageMedia imageMedia) {
        return new ImageMediaJpaEntity(
                imageMedia.getChecksum(),
                imageMedia.getName(),
                imageMedia.getLocation()
        );
    }

    public ImageMedia toDomain() {
        return ImageMedia.with(
                this.id,
                this.name,
                this.filePath
        );
    }
}
