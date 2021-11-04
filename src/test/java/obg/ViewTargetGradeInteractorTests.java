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
        generateCorrectGradeResponse("A-", 4, 3,2);
    }

    @Test
    public void interactorGeneratesCorrectResponseForDPlus() {
        generateCorrectGradeResponse("D+", 1, 1, 0);
    }

    @Test
    public void interactorGeneratesCorrectResponseForF(){
        generateCorrectGradeResponse("F", 0, 0, 0);
    }

    private void generateCorrectGradeResponse(String grade, int b, int c, int e) {
        TargetGradeRequirementsGateway gradeGateway = new TargetGradeRequirementsGateway();
        ViewTargetGradeInteractor gradeInteractor = new ViewTargetGradeInteractor(gradeGateway);
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), grade);
        Response actualResponse = gradeInteractor.handle(request);
        TargetGradeRequirementsResponse expectedResponse = generateExpectedResponse(grade, b, c, e);
        assertEquals(expectedResponse, actualResponse);
    }

    private TargetGradeRequirementsResponse generateExpectedResponse(String grade, int b, int c, int e) {
        TargetGradeRequirementsResponse expectedResponse = new TargetGradeRequirementsResponse(grade);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.BASIC, b);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.CORE, c);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.EXTRA, e);
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
