package obg;

import obg.core.ErrorResponse;
import obg.core.entity.*;
import obg.gateway.InstructorCanDeleteAttemptGateway;
import obg.interactor.InstructorCanDeleteAttemptInteractor;
import obg.presenter.InstructorCanDeleteAttemptPresenter;
import obg.request.InstructorCanDeleteAttemptRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class instructorCanDeleteAttemptTest {

    private InstructorCanDeleteAttemptPresenter presenter;
    private InstructorCanDeleteAttemptGateway gateway;
    private InstructorCanDeleteAttemptInteractor interactor;
    private InstructorCanDeleteAttemptRequest request;
    private UUID attemptUUID;
    private UUID instructorUUID;
    private Instructor instructor;
    private Attempt attempt;
    private Enrollment enrollment;

    @Before
    public void setUp() throws Exception {
        presenter = mock(InstructorCanDeleteAttemptPresenter.class);
        gateway = mock(InstructorCanDeleteAttemptGateway.class);
        interactor = new InstructorCanDeleteAttemptInteractor(gateway);
        request = new InstructorCanDeleteAttemptRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        instructorUUID = UUIDfromString(request.instructorId);
        attemptUUID = UUIDfromString(request.attemptId);
        instructor = new Instructor(request.instructorId, "first", "last");
        enrollment = new Enrollment(new Course(UUID.randomUUID(), "course"), new Student(UUID.randomUUID(), "student"));
        attempt = new Attempt("obj1", 1, enrollment);
    }

    @Test
    public void canCreateRequest() {
        String instructorId = UUID.randomUUID().toString();
        String attemptId = UUID.randomUUID().toString();
        InstructorCanDeleteAttemptRequest request = new InstructorCanDeleteAttemptRequest(instructorId, attemptId);
        assertEquals(instructorId, request.instructorId);
        assertEquals(attemptId, request.attemptId);
    }

    @Test
    public void interactorPresentsErrorForInvalidInstructor() {
        when(gateway.getInstructor(instructorUUID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(instructorUUID);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
        verify(gateway, Mockito.times(0)).removeAttempt(attempt.getLongId());
    }

    @Test
    public void interactorPresentsErrorForInvalidAttempt() {
        when(gateway.getInstructor(instructorUUID)).thenReturn(instructor);
        when(gateway.getAttempt(attemptUUID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(gateway).getAttempt(attemptUUID);
        verify(presenter).reportError(ErrorResponse.INVALID_ATTEMPT);
        verify(gateway, Mockito.times(0)).removeAttempt(attempt.getLongId());
    }

    @Test
    public void interactorReportsErrorForInvalidCourseInstructor() {
        when(gateway.getInstructor(instructorUUID)).thenReturn(instructor);
        when(gateway.getAttempt(attemptUUID)).thenReturn(attempt);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.NOT_COURSE_INSTRUCTOR);
        verify(gateway, Mockito.times(0)).removeAttempt(attempt.getLongId());
    }

    @Test
    public void interactorUsesGatewayToRemoveAttemptAfterSuccessfulChecks() {
        when(gateway.getInstructor(instructorUUID)).thenReturn(instructor);
        when(gateway.getAttempt(attemptUUID)).thenReturn(attempt);
        enrollment.getEnrolledCourse().setInstructor(instructor);
        interactor.handle(request, presenter);
        verify(gateway, Mockito.times(1)).removeAttempt(attempt.getLongId());
        verify(presenter).presentSuccessfulRemove("Successfully Removed");
    }

    private UUID UUIDfromString(String stringId) {
        return UUID.fromString(stringId);
    }
}
