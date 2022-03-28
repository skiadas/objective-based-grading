package obg;

import obg.core.Presenter;
import obg.gateway.ComputeObjectiveGradeGateway;
import obg.interactor.ComputeObjectiveGradeInteractor;
import obg.request.ComputeObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ComputeObjectiveGradeTest {

    private ComputeObjectiveGradeGateway gateway;
    private Presenter presenter;
    private ComputeObjectiveGradeInteractor interactor;
    private ComputeObjectiveGradeRequest request;

    @Before
    public void setUp() throws Exception {
//        request = ComputeObjectiveGradeRequest(courseId);
        gateway = mock(ComputeObjectiveGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ComputeObjectiveGradeInteractor(gateway, presenter);
    }

    @Test
    @Ignore
    public void canCreateComputeObjectiveGradeRequest() {
        ComputeObjectiveGradeInteractor interactor = new ComputeObjectiveGradeInteractor(gateway, presenter);
        interactor.handle(request);
    }
}
