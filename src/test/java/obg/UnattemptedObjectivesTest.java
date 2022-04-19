package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.UnattemptedObjectiveGateway;
import obg.interactor.UnattemptedObjectiveInteractor;
import obg.request.UnattemptedObjectiveRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class UnattemptedObjectivesTest {

    private UnattemptedObjectiveGateway gateway;
    private UnattemptedObjectiveRequest request;
    private UnattemptedObjectiveInteractor interactor;
    private Presenter presenter;
    private Enrollment enroll;
    private Attempt attempt;
    private ArrayList<String> objs;

    @Before
    public void setUp() {
        gateway = mock(UnattemptedObjectiveGateway.class);
        request = new UnattemptedObjectiveRequest("Dave", UUID.randomUUID());
        presenter = mock(Presenter.class);
        interactor = new UnattemptedObjectiveInteractor(gateway, presenter);
        enroll = new Enrollment(new Course(request.courseId, "course1"), new Student(UUID.randomUUID(), request.studentId));
        enroll.getCourse().objectives.add("obj1");
        enroll.getCourse().objectives.add("obj2");
        attempt = new Attempt("obj2", 1, enroll);
        enroll.addAttempt(attempt);
        objs = new ArrayList<>();
        objs.add("obj1");
    }

    @Test
    public void InvalidEnrollmentTest(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(null);
        interactor.handle(request);
        System.out.println("Value of student id: " + request.studentId);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    @Ignore
    public void ReturnStudentUnattemptedObjectives(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        interactor.handle(request);
        verify(presenter).presentUnattemptedObjectives(objs);
    }
}
