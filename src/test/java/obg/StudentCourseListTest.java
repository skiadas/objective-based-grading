package obg;

import obg.core.Response;
import obg.gateway.StudentCourseListGateway;
import obg.interactor.StudentCourseListInteractor;
import obg.request.StudentCourseListRequest;
import obg.response.ErrorResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentCourseListTest {

    @Test
    public void isInvalidStudent(){
        StudentCourseListGateway gateway = mock(StudentCourseListGateway.class);
        StudentCourseListInteractor interactor = new StudentCourseListInteractor(gateway);
        StudentCourseListRequest request = new StudentCourseListRequest("userName");
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidStudent(), response);
    }

}
