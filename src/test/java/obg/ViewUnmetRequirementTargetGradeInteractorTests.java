package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.ViewUnmetRequirementTargetGradeGateway;
import obg.interactor.ViewTargetGradeUnmetRequirementsInteractor;
import obg.request.ViewUnmetRequirementTargetGradeRequest;
import obg.response.TargetGradeRequirementsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ViewUnmetRequirementTargetGradeInteractorTests {
    private ViewUnmetRequirementTargetGradeRequest request;
    private ViewUnmetRequirementTargetGradeGateway gateway;
    private ViewTargetGradeUnmetRequirementsInteractor interactor;
    private Presenter presenter;

    @Before
    public void setUp() {
        presenter = mock(Presenter.class);
        request = new ViewUnmetRequirementTargetGradeRequest("student", UUID.randomUUID(), "A");
    }

    @Test
    public void noRequirementsForTargetGrade() {
        //when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(null);

//        Attempt attempt = new Attempt("obj1", 1, enrollment);
//        Attempt attemptAssigned = new Attempt("obj2", 1, enrollment);
//        AttemptList expectedList = new AttemptList();
//        enrollment.addAttempt(attemptAssigned);
//        when(gateway.getEnrollment(course, student)).thenReturn(enrollment);
//        interactor.handle(request, presenter);

        // Need this:
        //verify(presenter).presentTargetGradeUnmetRequirements(expectedList);
    }

    @Test
    public void oneRequirementsForTargetGrade() {
    }

    @Test
    public void twoRequirementsForTargetGrade() {
    }

    @Test
    public void threeRequirementsForTargetGrade() {
    }

    private void assertGeneratesCorrectResponse(String grade, int b, int c, int e) {
        ViewUnmetRequirementTargetGradeRequest request = new ViewUnmetRequirementTargetGradeRequest("student",UUID.randomUUID(), grade);
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
