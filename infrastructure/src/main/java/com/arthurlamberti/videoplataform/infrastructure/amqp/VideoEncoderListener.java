package com.arthurlamberti.videoplataform.infrastructure.amqp;

import com.arthurlamberti.videoplataform.application.media.update.UpdateMediaStatusCommand;
import com.arthurlamberti.videoplataform.application.media.update.UpdateMediaStatusUseCase;
import com.arthurlamberti.videoplataform.domain.video.MediaStatus;
import com.arthurlamberti.videoplataform.infrastructure.configuration.json.Json;
import com.arthurlamberti.videoplataform.infrastructure.video.models.VideoEncoderCompleted;
import com.arthurlamberti.videoplataform.infrastructure.video.models.VideoEncoderError;
import com.arthurlamberti.videoplataform.infrastructure.video.models.VideoEncoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VideoEncoderListener {

    private static final Logger log = LoggerFactory.getLogger(VideoEncoderListener.class);
    public static final String LISTENER_ID = "VideoEncoderListener";
    private final UpdateMediaStatusUseCase useCase;

    public VideoEncoderListener(final UpdateMediaStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.video-encoded.queue}")
    public void onVideoEncodedMessage(@Payload final String message) {
        final var aResult = Json.readValue(message, VideoEncoderResult.class);
        log.error("[message:video.listener.income] [status:completed] [payload:{}]", message);
        if (aResult instanceof VideoEncoderCompleted dto) {
            final var aCmd = new UpdateMediaStatusCommand(
                    MediaStatus.COMPLETED,
                    dto.id(),
                    dto.video().resourceId(),
                    dto.video().encodedVideoFolder(),
                    dto.video().filePath()
            );
            this.useCase.execute(aCmd);
        } else if (aResult instanceof VideoEncoderError err) {
            log.error("[message:video.listener.income] [status:error] [payload:{}]", message);
        } else {
            log.error("[message:video.listener.income] [status:unknown] [payload:{}]", message);
        }
    }
}
