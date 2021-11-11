package obg;

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
