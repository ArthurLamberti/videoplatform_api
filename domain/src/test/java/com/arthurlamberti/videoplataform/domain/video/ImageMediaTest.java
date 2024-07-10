package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageMediaTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallsNewImage_ShouldReturnInstance() {
        // given
        final var expectedChecksum = "abc";
        final var expectedName = "Banner.png";
        final var expectedLocation = "/images/ac";

        // when
        final var actualImage =
                ImageMedia.with(expectedChecksum, expectedName, expectedLocation);

        // then
        assertNotNull(actualImage);
        assertEquals(expectedChecksum, actualImage.getChecksum());
        assertEquals(expectedName, actualImage.getName());
        assertEquals(expectedLocation, actualImage.getLocation());
    }

    @Test
    public void givenTwoImagesWithSameChecksumAndLocation_whenCallsEquals_ShouldReturnTrue() {
        // given
        final var expectedChecksum = "abc";
        final var expectedLocation = "/images/ac";

        final var img1 =
                ImageMedia.with(expectedChecksum, "Random", expectedLocation);

        final var img2 =
                ImageMedia.with(expectedChecksum, "Simple", expectedLocation);

        // then
        assertEquals(img1, img2);
        assertNotSame(img1, img2);
    }

    @Test
    public void givenInvalidParams_whenCallsWith_ShouldReturnError() {
        assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with(null, "Random", "/images")
        );

        assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", null, "/images")
        );

        assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", "Random", null)
        );
    }
}
