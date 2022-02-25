package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.AssignAttemptScoreGateway;
import obg.interactor.AssignAttemptScoreInteractor;
import obg.request.AssignAttemptScoreRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class AssignAttemptScoreTest {


    private String attemptId;
    private String instructorId;
    private int score;
    private AssignAttemptScoreRequest request;
    private AssignAttemptScoreGateway gateway;
    private Presenter presenter;
    private AssignAttemptScoreInteractor interactor;
    private Attempt attempt;

    @Before
    public void setUp() throws Exception {
        UUID uuAttemptId = UUID.randomUUID();
        attemptId = uuAttemptId.toString();
        instructorId = UUID.randomUUID().toString();
        score = 4;
        request = new AssignAttemptScoreRequest(attemptId, score, instructorId);
        gateway = mock(AssignAttemptScoreGateway.class);
        presenter = mock(Presenter.class);
        interactor = new AssignAttemptScoreInteractor(gateway, presenter);
        attempt = new Attempt(uuAttemptId, "S1", 0, new Enrollment(new Course(UUID.randomUUID(), "test"), new Student(UUID.randomUUID(), "John")));
    }

    @Test
    public void canCreateAssignScoreRequest() {
        assertEquals(attemptId, request.attemptId);
        assertEquals(score, request.score);
        assertEquals(instructorId, request.instructorId);
    }

    @Test
    public void canCreateAssignAttemptScoreInteractor() {

        assertNotNull(interactor);
    }

    @Test
    public void interactorThrowsErrorForInvalidAttempt() {
        when(gateway.getAttempt(request.attemptId)).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getAttempt(request.attemptId);
        verify(presenter).reportError(ErrorResponse.INVALID_ATTEMPT);
    }

    @Test
    public void interactorThrowsErrorForInvalidInstructor() {
        when(gateway.getAttempt(request.attemptId)).thenReturn(attempt);
        when(gateway.getInstructor(request.instructorId)).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getInstructor(request.instructorId);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }
}
