package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.AssignAttemptScoreGateway;
import obg.interactor.AssignAttemptScoreInteractor;
import obg.request.AssignAttemptScoreRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class AssignAttemptScoreTest {


    private String stringAttemptId;
    private UUID uuidAttemptId;
    private Attempt attempt;
    private String stringInstructorId;
    private AssignAttemptScoreRequest request;
    private AssignAttemptScoreGateway gateway;
    private Presenter presenter;
    private AssignAttemptScoreInteractor interactor;
    private Instructor instructor;

    @Before
    public void setUp() {
        uuidAttemptId = UUID.randomUUID();
        stringAttemptId = uuidAttemptId.toString();
        attempt = new Attempt(uuidAttemptId, "S1", 0,
                new Enrollment(new Course(UUID.randomUUID(), "test"), new Student(UUID.randomUUID(), "John")));
        UUID uuidInstructorId = UUID.randomUUID();
        stringInstructorId = uuidInstructorId.toString();
        request = new AssignAttemptScoreRequest(stringAttemptId, 4, stringInstructorId);
        gateway = mock(AssignAttemptScoreGateway.class);
        presenter = mock(Presenter.class);
        interactor = new AssignAttemptScoreInteractor(gateway, presenter);
        instructor = new Instructor(stringInstructorId, "Professor", "Professorson");
    }

    @Test
    public void canCreateAssignScoreRequest() {
        assertEquals(stringAttemptId, request.attemptId);
        assertEquals(4, request.score);
        assertEquals(stringInstructorId, request.instructorId);
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

    @Test
    public void interactorThrowsErrorForInvalidCourseInstructor() {
        when(gateway.getAttempt(request.attemptId)).thenReturn(attempt);
        when(gateway.getInstructor(request.instructorId)).thenReturn(instructor);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE_INSTRUCTOR);
    }

    @Test
    public void interactorThrowsErrorForInvalidScore() {
        AssignAttemptScoreRequest badScoreRequest = new AssignAttemptScoreRequest(stringAttemptId, -1, stringInstructorId);
        attempt.getEnrollment().getEnrolledCourse().setInstructor(instructor);
        when(gateway.getAttempt(badScoreRequest.attemptId)).thenReturn(attempt);
        when(gateway.getInstructor(badScoreRequest.instructorId)).thenReturn(instructor);
        interactor.handle(badScoreRequest);
        verify(presenter).reportError(ErrorResponse.INVALID_SCORE);
    }

    @Test
    public void interactorUpdatesAttemptScoreWhenAllChecksAreSuccessful() {
        attempt.getEnrollment().getEnrolledCourse().setInstructor(instructor);
        when(gateway.getAttempt(request.attemptId)).thenReturn(attempt);
        when(gateway.getInstructor(request.instructorId)).thenReturn(instructor);
        interactor.handle(request);
        assertEquals(4, attempt.getScore());
    }
}
