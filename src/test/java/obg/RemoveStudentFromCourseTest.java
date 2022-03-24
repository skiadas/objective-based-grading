package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.gateway.RemoveStudentGateway;
import obg.interactor.RemoveStudentInteractor;
import obg.request.RemoveStudentRequest;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;


public class RemoveStudentFromCourseTest {
    @Test
    public void invalidEnrollmentThrowsError(){
        Course course1 = new Course(UUID.randomUUID(), "course1");
        Enrollment enrollment = new Enrollment(course1, null);
        RemoveStudentGateway gateway =  mock(RemoveStudentGateway.class);
        Presenter presenter = mock(Presenter.class);
        when(gateway.getEnrollment(enrollment)).thenReturn(null);
        RemoveStudentInteractor interactor = new RemoveStudentInteractor(gateway);
        RemoveStudentRequest request = new RemoveStudentRequest(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }
}
