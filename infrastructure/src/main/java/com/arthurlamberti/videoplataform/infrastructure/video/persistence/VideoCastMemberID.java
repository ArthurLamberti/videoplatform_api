package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class VideoCastMemberID implements Serializable {
    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "cast_member_id", nullable = false)
    private String castMemberId;

    public static VideoCastMemberID from(final String videoId, final String castMemberId) {
        return new VideoCastMemberID(videoId, castMemberId);
    }
}
