package application.video.media.update;

import application.UseCaseTest;
import com.arthurlamberti.videoplataform.application.media.update.DefaultUpdateMediaStatusUseCase;
import com.arthurlamberti.videoplataform.application.media.update.UpdateMediaStatusCommand;
import com.arthurlamberti.videoplataform.domain.Fixture;
import com.arthurlamberti.videoplataform.domain.resource.VideoMediaType;
import com.arthurlamberti.videoplataform.domain.video.MediaStatus;
import com.arthurlamberti.videoplataform.domain.video.Video;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateMediaStatusUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultUpdateMediaStatusUseCase useCase;

    @Mock
    private VideoGateway videoGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway);
    }

    @Test
    public void givenCommandForVideo_whenIsValid_shouldUpdateStatusAndEncodedLocation() {
        // given
        final var expectedStatus = MediaStatus.COMPLETED;
        final var expectedFolder = "encoded_media";
        final var expectedFilename = "filename.mp4";
        final var expectedType = VideoMediaType.VIDEO;
        final var expectedMedia = Fixture.Videos.audioVideo(expectedType);

        final var aVideo = Fixture.Videos.systemDesign()
                .updateVideoMedia(expectedMedia);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any()))
                .thenReturn(Optional.of(aVideo));

        when(videoGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var aCmd = UpdateMediaStatusCommand.with(
                expectedStatus,
                expectedId.getValue(),
                expectedMedia.getId(),
                expectedFolder,
                expectedFilename
        );

        // when
        this.useCase.execute(aCmd);

        // then
        verify(videoGateway, times(1)).findById(eq(expectedId));

        final var captor = ArgumentCaptor.forClass(Video.class);

        verify(videoGateway, times(1)).update(captor.capture());

        final var actualVideo = captor.getValue();

        Assertions.assertTrue(actualVideo.getTrailer().isEmpty());

        final var actualVideoMedia = actualVideo.getVideo().get();

        Assertions.assertEquals(expectedMedia.getId(), actualVideoMedia.getId());
        Assertions.assertEquals(expectedMedia.getRawLocation(), actualVideoMedia.getRawLocation());
        Assertions.assertEquals(expectedMedia.getChecksum(), actualVideoMedia.getChecksum());
        Assertions.assertEquals(expectedStatus, actualVideoMedia.getStatus());
        Assertions.assertEquals(expectedFolder.concat("/").concat(expectedFilename), actualVideoMedia.getEncodedLocation());
    }

    @Test
    public void givenCommandForVideo_whenIsValidForProcessing_shouldUpdateStatusAndEncodedLocation() {
        // given
        final var expectedStatus = MediaStatus.PROCESSING;
        final String expectedFolder = null;
        final String expectedFilename = null;
        final var expectedType = VideoMediaType.VIDEO;
        final var expectedMedia = Fixture.Videos.audioVideo(expectedType);

        final var aVideo = Fixture.Videos.systemDesign()
                .updateVideoMedia(expectedMedia);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any()))
                .thenReturn(Optional.of(aVideo));

        when(videoGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var aCmd = UpdateMediaStatusCommand.with(
                expectedStatus,
                expectedId.getValue(),
                expectedMedia.getId(),
                expectedFolder,
                expectedFilename
        );

        // when
        this.useCase.execute(aCmd);

        // then
        verify(videoGateway, times(1)).findById(eq(expectedId));

        final var captor = ArgumentCaptor.forClass(Video.class);

        verify(videoGateway, times(1)).update(captor.capture());

        final var actualVideo = captor.getValue();

        Assertions.assertTrue(actualVideo.getTrailer().isEmpty());

        final var actualVideoMedia = actualVideo.getVideo().get();

        Assertions.assertEquals(expectedMedia.getId(), actualVideoMedia.getId());
        Assertions.assertEquals(expectedMedia.getRawLocation(), actualVideoMedia.getRawLocation());
        Assertions.assertEquals(expectedMedia.getChecksum(), actualVideoMedia.getChecksum());
        Assertions.assertEquals(expectedStatus, actualVideoMedia.getStatus());
        Assertions.assertTrue(actualVideoMedia.getEncodedLocation().isBlank());
    }

    @Test
    public void givenCommandForTrailer_whenIsValid_shouldUpdateStatusAndEncodedLocation() {
        // given
        final var expectedStatus = MediaStatus.COMPLETED;
        final var expectedFolder = "encoded_media";
        final var expectedFilename = "filename.mp4";
        final var expectedType = VideoMediaType.TRAILER;
        final var expectedMedia = Fixture.Videos.audioVideo(expectedType);

        final var aVideo = Fixture.Videos.systemDesign()
                .updateTrailerMedia(expectedMedia);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any()))
                .thenReturn(Optional.of(aVideo));

        when(videoGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var aCmd = UpdateMediaStatusCommand.with(
                expectedStatus,
                expectedId.getValue(),
                expectedMedia.getId(),
                expectedFolder,
                expectedFilename
        );

        // when
        this.useCase.execute(aCmd);

        // then
        verify(videoGateway, times(1)).findById(eq(expectedId));

        final var captor = ArgumentCaptor.forClass(Video.class);

        verify(videoGateway, times(1)).update(captor.capture());

        final var actualVideo = captor.getValue();

        Assertions.assertTrue(actualVideo.getVideo().isEmpty());

        final var actualVideoMedia = actualVideo.getTrailer().get();

        Assertions.assertEquals(expectedMedia.getId(), actualVideoMedia.getId());
        Assertions.assertEquals(expectedMedia.getRawLocation(), actualVideoMedia.getRawLocation());
        Assertions.assertEquals(expectedMedia.getChecksum(), actualVideoMedia.getChecksum());
        Assertions.assertEquals(expectedStatus, actualVideoMedia.getStatus());
        Assertions.assertEquals(expectedFolder.concat("/").concat(expectedFilename), actualVideoMedia.getEncodedLocation());
    }

    @Test
    public void givenCommandForTrailer_whenIsValidForProcessing_shouldUpdateStatusAndEncodedLocation() {
        // given
        final var expectedStatus = MediaStatus.PROCESSING;
        final String expectedFolder = null;
        final String expectedFilename = null;
        final var expectedType = VideoMediaType.TRAILER;
        final var expectedMedia = Fixture.Videos.audioVideo(expectedType);

        final var aVideo = Fixture.Videos.systemDesign()
                .updateTrailerMedia(expectedMedia);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any()))
                .thenReturn(Optional.of(aVideo));

        when(videoGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var aCmd = UpdateMediaStatusCommand.with(
                expectedStatus,
                expectedId.getValue(),
                expectedMedia.getId(),
                expectedFolder,
                expectedFilename
        );

        // when
        this.useCase.execute(aCmd);

        // then
        verify(videoGateway, times(1)).findById(eq(expectedId));

        final var captor = ArgumentCaptor.forClass(Video.class);

        verify(videoGateway, times(1)).update(captor.capture());

        final var actualVideo = captor.getValue();

        Assertions.assertTrue(actualVideo.getVideo().isEmpty());

        final var actualVideoMedia = actualVideo.getTrailer().get();

        Assertions.assertEquals(expectedMedia.getId(), actualVideoMedia.getId());
        Assertions.assertEquals(expectedMedia.getRawLocation(), actualVideoMedia.getRawLocation());
        Assertions.assertEquals(expectedMedia.getChecksum(), actualVideoMedia.getChecksum());
        Assertions.assertEquals(expectedStatus, actualVideoMedia.getStatus());
        Assertions.assertTrue(actualVideoMedia.getEncodedLocation().isBlank());
    }

    @Test
    public void givenCommandForTrailer_whenIsInvalid_shouldDoNothing() {
        // given
        final var expectedStatus = MediaStatus.COMPLETED;
        final var expectedFolder = "encoded_media";
        final var expectedFilename = "filename.mp4";
        final var expectedType = VideoMediaType.TRAILER;
        final var expectedMedia = Fixture.Videos.audioVideo(expectedType);

        final var aVideo = Fixture.Videos.systemDesign()
                .updateTrailerMedia(expectedMedia);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any()))
                .thenReturn(Optional.of(aVideo));

        final var aCmd = UpdateMediaStatusCommand.with(
                expectedStatus,
                expectedId.getValue(),
                "randomId",
                expectedFolder,
                expectedFilename
        );

        // when
        this.useCase.execute(aCmd);

        // then
        verify(videoGateway, times(0)).update(any());
    }
}
