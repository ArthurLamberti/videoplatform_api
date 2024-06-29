package application.video.delete;

import application.UseCaseTest;
import com.arthurlamberti.videoplataform.application.video.delete.DefaultDeleteVideoUseCase;
import com.arthurlamberti.videoplataform.domain.exception.InternalErrorException;
import com.arthurlamberti.videoplataform.domain.video.MediaResourceGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoGateway;
import com.arthurlamberti.videoplataform.domain.video.VideoID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteVideoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteVideoUseCase defaultDeleteVideoUseCase;

    @Mock
    private VideoGateway videoGateway;

    @Mock
    private MediaResourceGateway mediaResourceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway, mediaResourceGateway);
    }

    @Test
    public void givenAValidID_whenCallsDeleteVideo_shouldDeleteIt() {
        final var expectedId = VideoID.unique();
        doNothing().when(videoGateway).deleteById(any());
        doNothing().when(mediaResourceGateway).clearResources(any());
        assertDoesNotThrow(()->this.defaultDeleteVideoUseCase.execute(expectedId.getValue()));
        verify(videoGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAnInvalidID_whenCallsDeleveVideo_shouldReturnOk(){
        final var expectedId = VideoID.from("123");
        doNothing().when(videoGateway).deleteById(any());
        assertDoesNotThrow(()->this.defaultDeleteVideoUseCase.execute(expectedId.getValue()));
        verify(videoGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidID_whenCallsVideoGatewayAndThowsException_shouldReceiveException(){
        final var expectedId = VideoID.from("123");
        doThrow(InternalErrorException.with("Error on delete video", new RuntimeException()))
                .when(videoGateway).deleteById(any());
        assertThrows(InternalErrorException.class,()->this.defaultDeleteVideoUseCase.execute(expectedId.getValue()));
        verify(videoGateway).deleteById(eq(expectedId));
    }

}
