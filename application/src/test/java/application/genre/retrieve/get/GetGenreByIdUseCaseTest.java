package application.genre.retrieve.get;

import application.UseCaseTest;
import com.arthurlamberti.videoplataform.application.genre.retrieve.get.DefaultGetGenreByIdUseCase;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.genre.Genre;
import com.arthurlamberti.videoplataform.domain.genre.GenreGateway;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static utils.StringUtils.generateValidString;

public class GetGenreByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetGenreByIdUseCase useCase;

    @Mock
    private GenreGateway genreGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(genreGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetGenre_shouldReturnGenre() {
        // given
        final var expectedName = generateValidString(5);
        final var expectedIsActive = true;
        final var expectedCategories = List.of(
                CategoryID.from(generateValidString(5)),
                CategoryID.from(generateValidString(5))
        );

        final var aGenre = Genre.newGenre(expectedName, expectedIsActive)
                .addCategories(expectedCategories);

        final var expectedId = aGenre.getId();

        when(genreGateway.findById(any()))
                .thenReturn(Optional.of(aGenre));
        // when
        final var actualGenre = useCase.execute(expectedId.getValue());

        // then
        Assertions.assertEquals(expectedId.getValue(), actualGenre.id());
        Assertions.assertEquals(expectedName, actualGenre.name());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(asString(expectedCategories), actualGenre.categories());
        Assertions.assertEquals(aGenre.getCreatedAt(), actualGenre.createdAt());
        Assertions.assertEquals(aGenre.getUpdatedAt(), actualGenre.updatedAt());
        Assertions.assertEquals(aGenre.getDeletedAt(), actualGenre.deletedAt());

        Mockito.verify(genreGateway, times(1)).findById(any());
    }

    @Test
    public void givenAValidId_whenCallsGetGenreAndDoesNotExists_shouldReturnNotFound() {
        // given
        final var expectedErrorMessage = "Genre with ID 123 was not found";

        final var expectedId = GenreID.from("123");

        when(genreGateway.findById(any()))
                .thenReturn(Optional.empty());

        // when
        final var actualException = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.execute(expectedId.getValue());
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
