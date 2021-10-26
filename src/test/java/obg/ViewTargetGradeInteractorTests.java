package obg;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ViewTargetGradeInteractorTests {

    @Test
    public void invalidCourseErrorResponse() {
        InvalidCourseGateway gateway = new InvalidCourseGateway();
        ViewTargetGradeInteractor interactor = new ViewTargetGradeInteractor(gateway);
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), "A");
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidCourseError(), response);
        assertEquals(request.courseId, gateway.providedCourseId);
    }

}
