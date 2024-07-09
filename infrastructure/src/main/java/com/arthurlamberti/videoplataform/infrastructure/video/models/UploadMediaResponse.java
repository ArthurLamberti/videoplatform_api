package com.arthurlamberti.videoplataform.infrastructure.video.models;

import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UploadMediaResponse(
        @JsonProperty("video_id") String videoId,
        @JsonProperty("media_type") VideoMediaType mediaType
) {
}
