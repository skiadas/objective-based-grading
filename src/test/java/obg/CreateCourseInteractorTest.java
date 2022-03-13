package obg;

import obg.core.ErrorResponse;
import obg.gateway.CreateCourseGateway;
import obg.interactor.CreateCourseInteractor;
import obg.presenter.CreateCoursePresenter;
import obg.request.CreateCourseRequest;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CreateCourseInteractorTest {

    private CreateCourseGateway gateway;
    private CreateCourseInteractor interactor;
    private CreateCourseRequest request;
    private CreateCoursePresenter presenter;

    @Before
    public void setUp() throws Exception {
        gateway = mock(CreateCourseGateway.class);
        interactor = new CreateCourseInteractor(gateway);
        request = new CreateCourseRequest("skiadas");
        presenter = mock(CreateCoursePresenter.class);
    }

    @Test
    public void whenCreatingCourseWithInvalidInstructorThenReportError() {
        when(gateway.getInstructor(request.instructorId)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(request.instructorId);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }
}
