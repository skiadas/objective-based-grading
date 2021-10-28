package obg;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ViewTargetGradeInteractorTests {

    private ViewTargetGradeRequest request;

    @Before
    public void setUp() {
        UUID courseId = UUID.randomUUID();
        request = new ViewTargetGradeRequest(courseId, "Z");
    }

    @Test
    public void invalidCourseErrorResponse() {
        InvalidCourseGateway gateway = new InvalidCourseGateway();
        ViewTargetGradeInteractor interactor = new ViewTargetGradeInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidCourseError(), response);
        assertEquals(request.courseId, gateway.providedCourseId);
    }

    @Test
    public void invalidLetterGradeResponse() {
        invalidLetterGradeGateway gateway = new invalidLetterGradeGateway();
        ViewTargetGradeInteractor interactor = new ViewTargetGradeInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidLetterGradeError(), response);
        assertEquals(request.letterGrade, gateway.providedLetterGrade);
    }

}
