package obg;

import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.AttemptRequestGateway;
import obg.interactor.AttemptRequestInteractor;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AttemptRequestTest {

    public UUID randID;
    private AttemptRequestRequest request;
    private AttemptRequestGateway gateway;
    private AttemptRequestInteractor interactor;
    private Course course;
    private Student student;
    private Presenter presenter;
    private Enrollment enrollment;

    @Before
    public void setUp() {
        presenter = mock(Presenter.class);
        gateway = mock(AttemptRequestGateway.class);
        randID = randomUUID();
        request = new AttemptRequestRequest("DoeJ24", randID, "L1");

        course = new Course(randID, "courseName");
        student = new Student(randomUUID(), request.studentID);
        interactor = new AttemptRequestInteractor(gateway);
        enrollment = new Enrollment(course, student);

    }

    @Test
    public void InteractorAskGatewayForCorrectCourse() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        interactor.handle(request, presenter);
        verify(gateway).getCourse(request.courseID);
    }

    @Test
    public void usingEnrollment_CheckingIfCourses_StudentAreTheSame() {
        when(gateway.getEnrollment(request.courseID, request.studentID)).thenReturn(enrollment);
        interactor.handle(request, presenter);
        assertEquals(course, enrollment.course);
        assertEquals(student, enrollment.student);
        verify(gateway).getEnrollment(request.courseID, request.studentID);
    }

    @Test
    public void CheckInvalidCourseErrorTest() {
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void InteractorAskGatewayForCorrectStudent() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        interactor.handle(request, presenter);
        verify(gateway).getStudent(request.studentID);
    }

    @Test
    public void checkInvalidStudentErrorTest() {
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.studentID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void InteractorAskGatewayForCorrectObjective() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.studentID)).thenReturn(student);
        interactor.handle(request, presenter);
        verify(gateway).objectiveInCourse(request.objective, request.courseID);
    }

    @Test
    public void InvalidObjectiveTest() {
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.studentID)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_OBJECTIVE);
    }

    @Test
    public void notEnrolledErrorTest() {
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.studentID)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.studentID, request.courseID)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
    }

    @Test
    public void VerifyPresenterIsCalledForNewAttempt(){
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.studentID)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.studentID, request.courseID)).thenReturn(true);
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), student, course);
        interactor.handle(request, presenter);
        verify(presenter).presentAttemptCreated(attempt);
    }
}
