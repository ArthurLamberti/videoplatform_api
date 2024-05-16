package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.video.AudioVideoMedia;
import com.arthurlamberti.videoplataform.domain.video.MediaStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "AudioMediaVideo")
@Table(name = "videos_video_media")
@AllArgsConstructor
@NoArgsConstructor
public class AudioVideoMediaJpaEntity {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "file_path", nullable = false)
    private String filePath;
    @Column(name = "encoded_path", nullable = false)
    private String encodedPath;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaStatus status;

    public static AudioVideoMediaJpaEntity from (final AudioVideoMedia audioVideoMedia) {
        return new AudioVideoMediaJpaEntity(
                audioVideoMedia.getChecksum(),
                audioVideoMedia.getName(),
                audioVideoMedia.getRawLocation(),
                audioVideoMedia.getEncodedLocation(),
                audioVideoMedia.getStatus()
        );
    }

    public AudioVideoMedia toDomain() {
        return AudioVideoMedia.with(this.id,this.name,this.filePath,this.encodedPath,this.status);
    }
}
