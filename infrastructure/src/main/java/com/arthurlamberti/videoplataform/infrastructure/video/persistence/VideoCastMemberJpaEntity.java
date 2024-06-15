package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "VideoCastMember")
@Table(name = "videos_cast_members")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class VideoCastMemberJpaEntity {

    @EmbeddedId
    private VideoCastMemberID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private VideoJpaEntity video;

    public static VideoCastMemberJpaEntity from(final VideoJpaEntity entity, final CastMemberID castMemberID) {
        return new VideoCastMemberJpaEntity(
                VideoCastMemberID.from(entity.getId().toString(), castMemberID.getValue()),
                entity
        );
    }
}
