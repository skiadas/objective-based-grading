package obg;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import obg.gateway.ViewTargetGradeGateway;
import obg.interactor.ViewTargetGradeInteractor;
import obg.request.ViewTargetGradeRequest;
import obg.core.ErrorResponse;
import obg.response.TargetGradeRequirementsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ViewTargetGradeInteractorTests {
    private ViewTargetGradeRequest request;
    private ViewTargetGradeGateway gateway;
    private ViewTargetGradeInteractor interactor;
    private Presenter presenter;

    @Before
    public void setUp() {
        UUID courseId = UUID.randomUUID();
        request = new ViewTargetGradeRequest(courseId, "Z");
        gateway = mock(ViewTargetGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ViewTargetGradeInteractor(gateway);
    }

    @Test
    public void errorResponseForInvalidCourse() {
        when(gateway.getCourse(request.courseId))
                .thenReturn(null);
        interactor.handle(request, presenter);
        String errorMessage = ErrorResponse.INVALID_COURSE;
        verify(presenter).reportError(errorMessage);
    }

    @Test
    public void errorResponseForInvalidLetterGrade() {
        when(gateway.getCourse(request.courseId))
                .thenReturn(new Course(UUID.randomUUID(), "course"));
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_LETTER_GRADE);
    }

    @Test
    public void interactorGeneratesCorrectResponse() {
        assertGeneratesCorrectResponse("A-", 4, 3,2);
    }

    @Test
    public void interactorGeneratesCorrectResponseForDPlus() {
        assertGeneratesCorrectResponse("D+", 1, 1, 0);
    }

    @Test
    public void interactorGeneratesCorrectResponseForF(){
        assertGeneratesCorrectResponse("F", 0, 0, 0);
    }

    private void assertGeneratesCorrectResponse(String grade, int b, int c, int e) {
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), grade);
        when(gateway.getCourse(request.courseId)).thenReturn(new Course(UUID.randomUUID(), "course"));
        interactor.handle(request, presenter);
        verify(presenter).presentTargetGradeRequirements(generateExpectedResponse(grade, b, c, e));
    }

    private TargetGradeRequirementsResponse generateExpectedResponse(String grade, int b, int c, int e) {
        TargetGradeRequirementsResponse expectedResponse = new TargetGradeRequirementsResponse(grade);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.BASIC, b);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.CORE, c);
        expectedResponse.objectiveRequirements.put(ObjectiveGroup.EXTRA, e);
        return expectedResponse;
    }
}
