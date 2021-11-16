package obg;

import obg.core.Presenter;
import obg.gateway.StudentCourseListGateway;
import obg.interactor.StudentCourseListInteractor;
import obg.request.StudentCourseListRequest;
import obg.core.ErrorResponse;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StudentCourseListTest {

    @Test
    public void isInvalidStudent(){
        StudentCourseListGateway gateway = mock(StudentCourseListGateway.class);
        StudentCourseListInteractor interactor = new StudentCourseListInteractor(gateway);
        StudentCourseListRequest request = new StudentCourseListRequest("userName");
        Presenter presenter = mock(Presenter.class);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

}
