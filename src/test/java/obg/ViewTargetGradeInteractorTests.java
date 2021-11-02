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
        assertEquals(ErrorResponse.invalidCourse(), response);
        assertEquals(request.courseId, gateway.providedCourseId);
    }

    @Test
    public void invalidLetterGradeResponse() {
        InvalidLetterGradeGateway gateway = new InvalidLetterGradeGateway();
        ViewTargetGradeInteractor interactor = new ViewTargetGradeInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidLetterGrade(), response);
        assertEquals(request.letterGrade, gateway.providedLetterGrade);
    }

    @Test
    public void interactorGeneratesCorrectResponse() {
        TargetGradeRequirementsGateway gateway = new TargetGradeRequirementsGateway();
        ViewTargetGradeInteractor interactor = new ViewTargetGradeInteractor(gateway);
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), "A");
        Response actualResponse = interactor.handle(request);
        TargetGradeRequirementsResponse expectedResponse = generateExpectedResponse();
        assertEquals(expectedResponse, actualResponse);
        //todo: assert statement that checks interactor uses gateway to get response (their gradeRequirements are the same)
    }

    private TargetGradeRequirementsResponse generateExpectedResponse() {
        TargetGradeRequirementsResponse expectedResponse = new TargetGradeRequirementsResponse("A");
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.BASIC, 4);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.CORE, 4);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.EXTRA, 3);
        return expectedResponse;
    }

    private static class InvalidCourseGateway implements ViewTargetGradeGateway {
        UUID providedCourseId;

        public Course getCourse(UUID courseId) {
            providedCourseId = courseId;
            return null;
        }

        public boolean isValidLetterGrade(String letterGrade) {
            return false;
        }

    }
    private static class InvalidLetterGradeGateway implements ViewTargetGradeGateway {
        public String providedLetterGrade;

        public Course getCourse(UUID courseId) {
            return new Course(UUID.randomUUID(), "course1");
        }

        public boolean isValidLetterGrade(String letterGrade) {
            providedLetterGrade = letterGrade;
            return false;
        }
    }
    private static class TargetGradeRequirementsGateway implements ViewTargetGradeGateway {
        public Course getCourse(UUID courseId) {
            return new Course(UUID.randomUUID(), "course1");
        }

        public boolean isValidLetterGrade(String letterGrade) {
            return true;
        }
    }
}
