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
        student = new Student(randomUUID(), request.studentId);
        interactor = new AttemptRequestInteractor(gateway);
        enrollment = new Enrollment(course, student, "12-15-2020", false);

    }

    @Test
    public void usingEnrollment_CheckingIfCourses_StudentAreTheSame() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment);
        interactor.handle(request, presenter);
        assertEquals(course, enrollment.course);
        assertEquals(student, enrollment.student);
    }

    @Test
    public void VerifyPresenterIsCalledForNewAttempt(){
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment);
        when(gateway.objectiveInCourse(request.objective, request.courseId)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.studentId, request.courseId)).thenReturn(true);
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enrollment);
        interactor.handle(request, presenter);
        verify(presenter).presentAttempt(attempt);
    }

    @Test
    public void canSubtractRemainingAttempts() {
        Enrollment enrollment_3 = new Enrollment(course, student,40 );
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment_3);
        when(gateway.objectiveInCourse(request.objective, request.courseId)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.studentId, request.courseId)).thenReturn(true);
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enrollment_3);
        interactor.handle(request, presenter);
        verify(presenter).presentAttempt(attempt);
        assertEquals(39,enrollment_3.getRemainingAttempts());
    }

    @Test
    public void checkInvalidAttemptNumberTest() {
        Enrollment enrollment_4 = new Enrollment(course, student,0 );
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment_4);
        when(gateway.objectiveInCourse(request.objective, request.courseId)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.studentId, request.courseId)).thenReturn(true);
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enrollment_4);
        interactor.handle(request, presenter);
        verify(presenter).presentAttempt(attempt);
        verify(presenter).reportError(ErrorResponse.INVALID_ATTEMPTNUMBER);
    }
}
