package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.RemoveStudentGateway;
import obg.interactor.RemoveStudentInteractor;
import obg.request.RemoveStudentRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;


public class RemoveStudentFromCourseTest {
    private Course course1;
    private Student student1;
    private RemoveStudentGateway gateway;
    private Presenter presenter;

    @Before
    public void setUp() throws Exception {
        course1 = new Course(UUID.randomUUID(), "course1");
        student1 = new Student(UUID.randomUUID(), "student1");
        gateway =  mock(RemoveStudentGateway.class);
        presenter = mock(Presenter.class);
    }

    @Test
    public void invalidEnrollmentThrowsError(){
        Enrollment enrollment = new Enrollment(course1, null);
        when(gateway.getEnrollment(enrollment)).thenReturn(null);
        RemoveStudentInteractor interactor = new RemoveStudentInteractor(gateway);
        RemoveStudentRequest request = new RemoveStudentRequest(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void invalidEnrollmentWhenStudentWithdraws(){
        Enrollment enrollment = new Enrollment(course1, student1, "today", false);
        enrollment.setWithdrawn(true);
        when(gateway.getEnrollment(enrollment)).thenReturn(null);
        RemoveStudentInteractor interactor = new RemoveStudentInteractor(gateway);
        RemoveStudentRequest request = new RemoveStudentRequest(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void validEnrollment(){
        Enrollment enrollment = new Enrollment(course1, student1, "today", false);
        when(gateway.getEnrollment(enrollment)).thenReturn(enrollment);
        RemoveStudentInteractor interactor = new RemoveStudentInteractor(gateway);
        RemoveStudentRequest request = new RemoveStudentRequest(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).presentsRemovedStudent(enrollment);
    }
}
