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
    public void checkInvalidStudentErrorTest() {
        Enrollment enrollment_2 = new Enrollment(course, null, "12-15-2020", false);
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment_2);
        when(gateway.getEnrolledStudent()).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void CheckInvalidCourseErrorTest() {
        Enrollment enrollment_2 = new Enrollment(null, student, "12-15-2020", false);
        when(gateway.getAttemptNumber()).thenReturn(1);
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enrollment_2);
        when(gateway.getEnrolledCourse()).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void removeStudentResultsInInvalidErrorMessage(){
        Student student1 = new Student(UUID.randomUUID(), "James");
        Course course1 = new Course(UUID.randomUUID(), "course1");
        Course course2 = new Course(UUID.randomUUID(), "course2");
        Instructor instructor = new Instructor(UUID.randomUUID().toString(), "first", "last");
        Enrollment enrollment = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment2 = new Enrollment(course2, student1, "today", false);
        enrollment.removeStudent(instructor, student1.getStudentId(), course1.getCourseId());
        when(gateway.getEnrollment(course2.getCourseId(), student1.userName)).thenReturn(enrollment2);
        when(gateway.getEnrollment(course1.getCourseId(), student1.userName)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
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
}
