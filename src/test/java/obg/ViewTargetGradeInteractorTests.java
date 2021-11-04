package obg;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewTargetGradeInteractorTests {
    private ViewTargetGradeRequest request;
    private ViewTargetGradeGateway gateway;
    private ViewTargetGradeInteractor interactor;

    @Before
    public void setUp() {
        UUID courseId = UUID.randomUUID();
        request = new ViewTargetGradeRequest(courseId, "Z");
        gateway = mock(ViewTargetGradeGateway.class);
        interactor = new ViewTargetGradeInteractor(gateway);
    }

    @Test
    public void errorResponseForInvalidCourse() {
        when(gateway.getCourse(request.courseId))
                .thenReturn(null);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidCourse(), response);
    }

    @Test
    public void errorResponseForInvalidLetterGrade() {
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), "Z");
        when(gateway.getCourse(request.courseId))
                .thenReturn(new Course(UUID.randomUUID(), "course"));
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidLetterGrade(), response);
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
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), grade);
        when(gateway.getCourse(request.courseId)).thenReturn(new Course(UUID.randomUUID(), "course"));
        Response actualResponse = interactor.handle(request);
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
}
