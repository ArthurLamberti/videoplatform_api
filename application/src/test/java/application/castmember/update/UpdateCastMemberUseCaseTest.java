package application.castmember.update;

import application.Fixture;
import application.UseCaseTest;
import com.arthurlamberti.videoplataform.application.castmember.update.DefaultUpdateCastMemberUseCase;
import com.arthurlamberti.videoplataform.application.castmember.update.UpdateCastMemberCommand;
import com.arthurlamberti.videoplataform.application.castmember.update.UpdateCastMemberUseCase;
import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberGateway;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;
import com.arthurlamberti.videoplataform.domain.exception.NotFoundException;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateCastMemberUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateCastMemberUseCase useCase;

    @Mock
    private CastMemberGateway castMemberGateway;


    @Override
    protected List<Object> getMocks() {
        return null;
    }

    @Test
    public void giverAValidCommand_whenCallUpdateCastMember_shouldReturnItsIdentifies() {
        final var aMember = CastMember.newMember("nam", CastMemberType.DIRECTOR);

        final var expectedId = aMember.getId();
        final var expectedName = Fixture.name();
        final var expectedType = CastMemberType.ACTOR;

        final var aCommand = UpdateCastMemberCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedType
        );

        when(castMemberGateway.findById(any())).thenReturn(Optional.of(CastMember.with(aMember)));
        when(castMemberGateway.update(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        assertNotNull(actualOutput);
        assertEquals(expectedId.getValue(), actualOutput.id());
    }


    @Test
    public void giverAnInvalidName_whenCallUpdateCastMember_shouldReturnNotificationException() {
        final var aMember = CastMember.newMember("nam", CastMemberType.DIRECTOR);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var expectedId = aMember.getId();
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;

        final var aCommand = UpdateCastMemberCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedType
        );

        when(castMemberGateway.findById(any())).thenReturn(Optional.of(aMember));

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
    @Test
    public void giverAnInvalidType_whenCallUpdateCastMember_shouldReturnNotificationException() {
        final var aMember = CastMember.newMember("nam", CastMemberType.DIRECTOR);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var expectedId = aMember.getId();
        final String expectedName = Fixture.name();
        final CastMemberType expectedType = null;

        final var aCommand = UpdateCastMemberCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedType
        );

        when(castMemberGateway.findById(any())).thenReturn(Optional.of(aMember));

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void giverAnInvalidId_whenCallUpdateCastMember_shouldReturnNotificationException() {
        final var aMember = CastMember.newMember("nam", CastMemberType.DIRECTOR);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "CastMember with ID 123 was not found";

        final var expectedId = "123";
        final String expectedName = Fixture.name();
        final CastMemberType expectedType = Fixture.CastMember.type();

        final var aCommand = UpdateCastMemberCommand.with(
                expectedId,
                expectedName,
                expectedType
        );

        when(castMemberGateway.findById(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
