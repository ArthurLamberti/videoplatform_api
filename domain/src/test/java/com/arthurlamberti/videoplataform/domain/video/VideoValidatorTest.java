package com.arthurlamberti.videoplataform.domain.video;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.exception.DomainException;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.utils.StringUtils;
import com.arthurlamberti.videoplataform.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Set;

public class VideoValidatorTest {
    @Test
    public void givenNullTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final String expectedTitle = null;
        final var expectedDescription = StringUtils.generateValidString(400);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenEmptyTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "";
        final var expectedDescription = StringUtils.generateValidString(400);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be empty";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenTitleWithLengthGreaterThan255_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = StringUtils.generateValidString(256);
        final var expectedDescription = StringUtils.generateValidString(400);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' must be between 1 and 255 characters";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenEmptyDescription_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = StringUtils.generateValidString(20);
        final var expectedDescription = "";
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenDescriptionWithLengthGreaterThan4000_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = StringUtils.generateValidString(20);;
        final var expectedDescription = StringUtils.generateValidString(4100);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 1 and 4000 characters";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenNullLaunchedAt_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = StringUtils.generateValidString(20);
        final var expectedDescription = StringUtils.generateValidString(20);
        final Year expectedLaunchedAt = null;
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'launchedAt' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenNullRating_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = StringUtils.generateValidString(20);
        final var expectedDescription = StringUtils.generateValidString(20);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final Rating expectedRating = null;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'rating' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedRating,
                expectedOpened,
                expectedPublished,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        final var validator = new VideoValidator(actualVideo, new ThrowsValidationHandler());

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> validator.validate()
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

}
