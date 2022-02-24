package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.AssignAttemptScoreGateway;
import obg.interactor.AssignAttemptScoreInteractor;
import obg.request.AssignAttemptScoreRequest;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class AssignAttemptScoreTest {

    @Test
    public void canCreateAssignScoreRequest() {
        String id = UUID.randomUUID().toString();
        String instructorId = UUID.randomUUID().toString();
        int score = 4;
        AssignAttemptScoreRequest request = new AssignAttemptScoreRequest(id, score, instructorId);
        assertEquals(id, request.attemptId);
        assertEquals(score, request.score);
    }

    @Test
    public void canCreateAssignAttemptScoreInteractor() {
        AssignAttemptScoreGateway gateway = mock(AssignAttemptScoreGateway.class);
        Presenter presenter = mock(Presenter.class);
        AssignAttemptScoreInteractor interactor = new AssignAttemptScoreInteractor(gateway, presenter);
        assertNotNull(interactor);
    }

    @Test
    public void interactorThrowsErrorForInvalidAttempt() {
        String attemptId = UUID.randomUUID().toString();
        String instructorId = UUID.randomUUID().toString();
        int score = 4;
        AssignAttemptScoreRequest request = new AssignAttemptScoreRequest(attemptId, score, instructorId);
        AssignAttemptScoreGateway gateway = mock(AssignAttemptScoreGateway.class);
        Presenter presenter = mock(Presenter.class);
        AssignAttemptScoreInteractor interactor = new AssignAttemptScoreInteractor(gateway, presenter);
        interactor.handle(request);
        when(gateway.getAttempt(request.attemptId)).thenReturn(null);
        verify(gateway).getAttempt(request.attemptId);
        verify(presenter).reportError(ErrorResponse.INVALID_ATTEMPT);
    }
}
