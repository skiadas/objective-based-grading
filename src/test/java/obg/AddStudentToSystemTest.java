package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Student;
import obg.gateway.AddStudentToSystemGateway;
import obg.interactor.AddStudentToSystemInteractor;
import obg.request.AddStudentToSystemRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddStudentToSystemTest {

    private Presenter presenter;
    private AddStudentToSystemRequest request;
    private AddStudentToSystemGateway gateway;
    private AddStudentToSystemInteractor interactor;
    private String username;
    Student student = new Student(UUID.randomUUID(), username);

    @Before
    public void setUp() throws Exception {
        username = UUID.randomUUID().toString();
        presenter = mock(Presenter.class);
        request = new AddStudentToSystemRequest(username);
        gateway = mock(AddStudentToSystemGateway.class);
        interactor = new AddStudentToSystemInteractor(gateway, presenter);
    }

    @Test
    public void canMakeAddStudentToSystemRequest() {
        AddStudentToSystemRequest addStudentRequest = new AddStudentToSystemRequest("username");
        assertEquals("username", addStudentRequest.userName);
    }

    @Test
    @Ignore
    public void whenStudentAlreadyInSystemThrowsError() {
        // AddStudentToSystemRequest request = new AddStudentToSystemRequest(username);
        AddStudentToSystemGateway gateway = mock(AddStudentToSystemGateway.class);
        username = "username";
        assertNotSame(username, request.userName);
        when(gateway.getStudentUsername((request.userName))).thenReturn(student);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }
}