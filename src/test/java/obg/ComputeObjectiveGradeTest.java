package obg;

import obg.core.Presenter;
import obg.gateway.ComputeObjectiveGradeGateway;
import obg.interactor.ComputeObjectiveGradeInteractor;
import obg.request.ComputeObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputeObjectiveGradeTest {

    private String courseId;
    private ComputeObjectiveGradeGateway gateway;
    private Presenter presenter;
    private ComputeObjectiveGradeInteractor interactor;
    private ComputeObjectiveGradeRequest request;

    @Before
    public void setUp() throws Exception {
        request = new ComputeObjectiveGradeRequest(courseId);
        gateway = mock(ComputeObjectiveGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ComputeObjectiveGradeInteractor(gateway, presenter);
    }

    @Test
    public void canCreateComputeObjectiveGradeRequest() {
        String courseId = "courseId";
        ComputeObjectiveGradeRequest request = new ComputeObjectiveGradeRequest(courseId);
        assertEquals(courseId, request.courseId);
    }

    @Test
    public void interactorCanRequestAttempts() {
        // when(gateway.getAttempts());
    }
}
