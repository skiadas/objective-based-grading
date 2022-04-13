package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Student;
import obg.gateway.AddStudentToSystemGateway;
import obg.interactor.AddStudentToSystemInteractor;
import obg.request.AddStudentToSystemRequest;
import org.junit.*;

import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddStudentToSystemTest {
    private Presenter presenter;
    private AddStudentToSystemRequest request;
    private AddStudentToSystemGateway gateway;
    private AddStudentToSystemInteractor interactor;

    @Before
    public void setUp() {
        presenter = mock(Presenter.class);
        request = new AddStudentToSystemRequest("username");
        gateway = mock(AddStudentToSystemGateway.class);
        interactor = new AddStudentToSystemInteractor(gateway, presenter);
    }

    @Test
    public void canMakeAddStudentToSystemRequest() {
        AddStudentToSystemRequest addStudentRequest = new AddStudentToSystemRequest("username");
        assertEquals("username", addStudentRequest.userName);
    }

    @Test
    public void whenStudentAlreadyInSystemThrowsError() {
        Student student = new Student(UUID.randomUUID(), request.userName);
        when(gateway.getStudentUsername((request.userName))).thenReturn(student);
        interactor.handle(request);
        verify(gateway).getStudentUsername(request.userName);
        verify(gateway, never()).addStudent(any());
        verify(presenter).reportError(ErrorResponse.EXISTING_STUDENT);
        verify(presenter, never()).presentAddedStudent(any());
    }

    @Test
    public void whenStudentNotInSystemThenSuccessful() {
        when(gateway.getStudentUsername((request.userName))).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getStudentUsername(request.userName);
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(gateway).addStudent(captor.capture());
        Student student = captor.getValue();
        assertEquals(request.userName, student.userName);
        verify(presenter).presentAddedStudent(student);
        verify(presenter, never()).reportError(any());
    }
}