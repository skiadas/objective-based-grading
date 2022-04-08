package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Student;
import obg.gateway.AddStudentToSystemGateway;
import obg.interactor.AddStudentToCourseInteractor;
import obg.interactor.AddStudentToSystemInteractor;
import obg.request.AddStudentToSystemRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddStudentToSystemTest {
    private AddStudentToSystemGateway gateway;


    @Test
    public void canMakeAddStudentToSystemRequest() {
        AddStudentToSystemRequest addStudentRequest = new AddStudentToSystemRequest("username");
        assertEquals("username", addStudentRequest.userName);
    }

    @Test
    public void whenStudentAlreadyInSystemThrowsError() {
        String username = "username";
        Presenter presenter = mock(Presenter.class);
        Student student = new Student(UUID.randomUUID(), username);
        AddStudentToSystemRequest request = new AddStudentToSystemRequest(username);
        AddStudentToSystemGateway gateway = mock(AddStudentToSystemGateway.class);
        AddStudentToSystemInteractor interactor = new AddStudentToSystemInteractor(gateway, presenter);
        assertSame(username, request.userName);
        when(gateway.getStudentUsername((request.userName))).thenReturn(student);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.EXISTING_STUDENT);
    }
}