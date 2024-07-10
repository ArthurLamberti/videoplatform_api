package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AudioVideoMediaTest  extends UnitTest {
    @Test
    public void givenValidParams_whenCallsNewAudioVideo_ShouldReturnInstance() {
        // given
        final var expectedChecksum = "abc";
        final var expectedName = "Banner.png";
        final var expectedRawLocation = "/images/ac";
        final var expectedEncodedLocation = "/images/ac-encoded";
        final var expectedStatus = MediaStatus.PENDING;

        // when
        final var actualVideo =
                AudioVideoMedia.with(expectedChecksum, expectedName, expectedRawLocation, expectedEncodedLocation, expectedStatus);

        // then
        assertNotNull(actualVideo);
        assertEquals(expectedChecksum, actualVideo.getChecksum());
        assertEquals(expectedName, actualVideo.getName());
        assertEquals(expectedRawLocation, actualVideo.getRawLocation());
        assertEquals(expectedEncodedLocation, actualVideo.getEncodedLocation());
        assertEquals(expectedStatus, actualVideo.getStatus());
    }

    @Test
    public void givenTwoVideosWithSameChecksumAndLocation_whenCallsEquals_ShouldReturnTrue() {
        // given
        final var expectedChecksum = "abc";
        final var expectedRawLocation = "/images/ac";

        final var img1 =
                AudioVideoMedia.with(expectedChecksum, "Random", expectedRawLocation, "", MediaStatus.PENDING);

        final var img2 =
                AudioVideoMedia.with(expectedChecksum, "Simple", expectedRawLocation, "", MediaStatus.PENDING);

        // then
        assertEquals(img1, img2);
        assertNotSame(img1, img2);
    }

    @Test
    public void givenInvalidParams_whenCallsWith_ShouldReturnError() {
        assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with(null, "Random", "/videos", "/videos", MediaStatus.PENDING)
        );

        assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", null, "/videos", "/videos", MediaStatus.PENDING)
        );

        assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", null, "/videos", MediaStatus.PENDING)
        );

        assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", "/videos", null, MediaStatus.PENDING)
        );

        assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", "/videos", "/videos", null)
        );
    }
}
