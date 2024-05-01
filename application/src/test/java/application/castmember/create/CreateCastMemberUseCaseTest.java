package application.castmember.create;

import application.Fixture;
import application.UseCaseTest;
import com.arthurlamberti.videoplataform.application.castmember.create.CreateCastMemberCommand;
import com.arthurlamberti.videoplataform.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class CreateCastMemberUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateCastMemberUseCase useCase;

    @Mock
    private CastMemberGateway castMemberGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(castMemberGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateCastMember_shouldReturnIt() {
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMember.type();

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        when(castMemberGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = useCase.execute(aCommand);

        assertNotNull(actualResult);
        verify(castMemberGateway).create(argThat(aMember ->
                Objects.nonNull(aMember.getId())
                        && Objects.equals(expectedName, aMember.getName())
                        && Objects.equals(expectedType, aMember.getType())
                        && Objects.nonNull(aMember.getCreatedAt())
                        && Objects.nonNull(aMember.getUpdatedAt())
        ));
    }

    @Test
    public void givenAnInvalidNullName_whenCallsCreateCastMember_shouldThrowsNotifcationException(){
        final String expectedName = null;
        final var expectedType = Fixture.CastMember.type();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        final var actualOutput = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getErrors().get(0).message());
        verify(castMemberGateway, times(0)).create(any());
    }
    @Test
    public void givenAnInvalidEmptyName_whenCallsCreateCastMember_shouldThrowsNotifcationException(){
        final String expectedName = " ";
        final var expectedType = Fixture.CastMember.type();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        final var actualOutput = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getErrors().get(0).message());
        verify(castMemberGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidNullType_whenCallsCreateCastMember_shouldThrowsNotifcationException(){
        final String expectedName = Fixture.name();
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        final var actualOutput = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getErrors().get(0).message());
        verify(castMemberGateway, times(0)).create(any());
    }
}
