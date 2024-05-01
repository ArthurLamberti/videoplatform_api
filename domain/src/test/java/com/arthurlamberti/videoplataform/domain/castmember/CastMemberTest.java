package com.arthurlamberti.videoplataform.domain.castmember;

import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.utils.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CastMemberTest {

    @Test
    public void givenAValidParams_whenCallsNewMembem_thenInstantaiteACastMember() {
        final var expectedName = "Vin Diesel";
        final var expectedType = CastMemberType.ACTOR;
        final var actualMember = CastMember.newMember(expectedName, expectedType);

        assertNotNull(actualMember);
        assertNotNull(actualMember.getId());
        assertEquals(expectedName, actualMember.getName());
        assertEquals(expectedType, actualMember.getType());
        assertNotNull(actualMember.getCreatedAt());
        assertNotNull(actualMember.getUpdatedAt());
        assertEquals(actualMember.getCreatedAt(), actualMember.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallsNewMember_shouldReceibeANotification() {
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";


        final var actualException = assertThrows(NotificationException.class, () -> CastMember.newMember(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallsNewMember_shouldReceibeANotification() {
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";


        final var actualException = assertThrows(NotificationException.class, () -> CastMember.newMember(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvalidNameWithLengthMoreThan255_whenCallsNewMember_shouldReceibeANotification() {
        final String expectedName = StringUtils.generateValidString(300);
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 1 and 255 characters";


        final var actualException = assertThrows(NotificationException.class, () -> CastMember.newMember(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    public void givenAnInvalidNullType_whenCallsNewMember_shouldReceibeANotification() {
        final String expectedName = "Vin diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";


        final var actualException = assertThrows(NotificationException.class, () -> CastMember.newMember(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallUpdate_shouldReceivedUpdated(){
        final String expectedName = "Vin diesel";
        final CastMemberType expectedType = CastMemberType.ACTOR;
        final var actualMember = CastMember.newMember("vin", CastMemberType.DIRECTOR);

        assertNotNull(actualMember);
        final var actualCreatedAt = actualMember.getCreatedAt();
        final var actualUpdatedAt = actualMember.getUpdatedAt();

        actualMember.update(expectedName, expectedType);

        assertNotNull(actualMember.getId());
        assertEquals(expectedName, actualMember.getName());
        assertEquals(expectedType, actualMember.getType());
        assertEquals(actualCreatedAt, actualMember.getCreatedAt());
        assertTrue(actualUpdatedAt.isBefore(actualMember.getUpdatedAt()));
    }

    @Test
    public void givenAnInvalidNullName_whenCallUpdate_shouldReceivedNotification(){
        final String expectedName = null;
        final CastMemberType expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var actualMember = CastMember.newMember("vin", CastMemberType.DIRECTOR);

        assertNotNull(actualMember);
        assertNotNull(actualMember.getId());

        final var actualException = assertThrows(NotificationException.class, () -> actualMember.update(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
    @Test
    public void givenAnInvalidEmptyName_whenCallUpdate_shouldReceivedNotification(){
        final String expectedName = " ";
        final CastMemberType expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var actualMember = CastMember.newMember("vin", CastMemberType.DIRECTOR);

        assertNotNull(actualMember);
        assertNotNull(actualMember.getId());

        final var actualException = assertThrows(NotificationException.class, () -> actualMember.update(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidGreatherName_whenCallUpdate_shouldReceivedNotification(){
        final String expectedName = StringUtils.generateValidString(300);
        final CastMemberType expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 1 and 255 characters";
        final var actualMember = CastMember.newMember("vin", CastMemberType.DIRECTOR);

        assertNotNull(actualMember);
        assertNotNull(actualMember.getId());

        final var actualException = assertThrows(NotificationException.class, () -> actualMember.update(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }


    @Test
    public void givenAnInvalidNullType_whenCallsUpdate_shouldReceibeANotification() {
        final String expectedName = "Vin diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";
        final var actualMember = CastMember.newMember("vin", CastMemberType.DIRECTOR);

        final var actualException = assertThrows(NotificationException.class, () -> actualMember.update(expectedName, expectedType));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
