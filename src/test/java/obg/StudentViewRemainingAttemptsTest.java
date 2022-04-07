package obg;

import obg.core.ErrorResponse;
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

    @Before
    public void setUp() throws Exception {
        String studentId = UUID.randomUUID().toString();
        UUID courseId = UUID.randomUUID();
        request = new StudentViewRemainingAttemptsRequest(studentId, courseId);
        gateway = mock(StudentViewRemainingAttemptsGateway.class);
        presenter = mock(StudentViewRemainingAttemptsPresenter.class);
        interactor = new StudentViewRemainingAttemptsInteractor(gateway, presenter);
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
        UUID studentId = UUID.randomUUID();
        when(gateway.getStudent(request.studentId)).thenReturn(new Student(studentId, "name"));
        when(gateway.getCourse(request.courseId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
        verify(gateway).getCourse(request.courseId);
    }
}
