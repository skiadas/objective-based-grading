package obg;

import obg.core.ErrorResponse;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.StudentViewRemainingAttemptsGateway;
import obg.interactor.StudentViewRemainingAttemptsInteractor;
import obg.presenter.StudentViewRemainingAttemptsPresenter;
import obg.request.StudentViewRemainingAttemptsRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class StudentViewRemainingAttemptsTest {

    private StudentViewRemainingAttemptsRequest request;
    private StudentViewRemainingAttemptsGateway gateway;
    private StudentViewRemainingAttemptsPresenter presenter;
    private StudentViewRemainingAttemptsInteractor interactor;
    private Student student;
    private Course course;


    @Before
    public void setUp() throws Exception {
        String studentId = UUID.randomUUID().toString();
        UUID courseId = UUID.randomUUID();
        request = new StudentViewRemainingAttemptsRequest(studentId, courseId);
        gateway = mock(StudentViewRemainingAttemptsGateway.class);
        presenter = mock(StudentViewRemainingAttemptsPresenter.class);
        interactor = new StudentViewRemainingAttemptsInteractor(gateway, presenter);
        student = new Student(UUID.fromString(studentId), "name");
        course = new Course(courseId,"course");
    }

    @Test
    public void canCreateStudentViewRemainingAttemptRequest() {
        String studentId = UUID.randomUUID().toString();
        UUID courseId = UUID.randomUUID();
        StudentViewRemainingAttemptsRequest request = new StudentViewRemainingAttemptsRequest(studentId, courseId);
        assertEquals(studentId, request.studentId);
        assertEquals(courseId, request.courseId);
    }

    @Test
    public void interactorReportsInvalidStudent() {
        when(gateway.getStudent(request.studentId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
        verify(gateway).getStudent(request.studentId);
    }

    @Test
    public void interactorReportsInvalidCourse() {
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getCourse(request.courseId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
        verify(gateway).getCourse(request.courseId);
    }

    @Test
    public void interactorReportsInvalidEnrollment() {
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getEnrollment(request.courseId,request.studentId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
        verify(gateway).getEnrollment(request.courseId,request.studentId);
    }

    @Test
    public void interactorReportsRemainingAttemptsAfterSuccessfulChecks() {
        Enrollment enrollment = new Enrollment(course,student,40);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getEnrollment(request.courseId,request.studentId)).thenReturn(enrollment);
        interactor.handle(request);
        verify(presenter).presentRemainingAttempts(enrollment.getRemainingAttempts());
        verify(gateway).getEnrollment(request.courseId,request.studentId);
    }
}
