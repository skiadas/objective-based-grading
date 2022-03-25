package obg;

import obg.core.ErrorResponse;
import obg.core.entity.Instructor;
import obg.gateway.CreateNewInstructorGateway;
import obg.interactor.CreateNewInstructorInteractor;
import obg.presenter.CreateNewInstructorPresenter;
import obg.request.CreateNewInstructorRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateNewInstructorTest {
    private CreateNewInstructorRequest request;
    private CreateNewInstructorPresenter presenter;
    private CreateNewInstructorGateway gateway;
    private CreateNewInstructorInteractor interactor;
    private String instructorId;
    private String first;
    private String last;
    private Instructor instructor;

    @Before
    public void setUp() throws Exception {
        instructorId = UUID.randomUUID().toString();
        first = "first";
        last = "last";
        instructor = new Instructor(instructorId, first, last);
        request = new CreateNewInstructorRequest(instructorId, first, last);
        presenter = mock(CreateNewInstructorPresenter.class);
        gateway = mock(CreateNewInstructorGateway.class);
        interactor = new CreateNewInstructorInteractor(gateway);
    }

    @Test
    public void tryToCreateNewInstructorButHasSameInstructorId() {
        when(gateway.getInstructor(instructorId)).thenReturn(instructor);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }

    @Test
    public void createNewInstructor() {
        when(gateway.getInstructor(instructorId)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).createNewInstructor(instructor);
    }
}
