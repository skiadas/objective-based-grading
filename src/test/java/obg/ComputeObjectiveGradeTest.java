package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.ComputeObjectiveGradeGateway;
import obg.interactor.ComputeObjectiveGradeInteractor;
import obg.request.ComputeObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ComputeObjectiveGradeTest {

    private UUID courseId;
    private ComputeObjectiveGradeGateway gateway;
    private Presenter presenter;
    private ComputeObjectiveGradeInteractor interactor;
    private ComputeObjectiveGradeRequest request;
    private Enrollment enroll;
    private Student studentId;

    @Before
    public void setUp() throws Exception {
        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();
        request = new ComputeObjectiveGradeRequest(courseId, "joe");
        gateway = mock(ComputeObjectiveGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ComputeObjectiveGradeInteractor(gateway, presenter);
        enroll = new Enrollment(new Course(request.courseId, "course"), new Student(studentId, request.studentId));
    }

    @Test
    public void invalidEnrollment() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

//    @Test
//    public void canCreateComputeObjectiveGradeRequest() {
//        String courseId = "courseId";
//        ComputeObjectiveGradeRequest request = new ComputeObjectiveGradeRequest(courseId, studentId);
//        assertEquals(courseId, request.courseId);
//    }
//
//    @Test
//    public void interactorCanRequestAttempts() {
//        // when(gateway.getAttempts());
//    }
}
