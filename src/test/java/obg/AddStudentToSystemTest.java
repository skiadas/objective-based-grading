package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.AddStudentToSystemGateway;
import obg.interactor.AddStudentToSystemInteractor;
import obg.request.AddStudentToSystemRequest;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddStudentToSystemTest {
    @Test
    public void canMakeAddStudentToSystemRequest(){
        AddStudentToSystemRequest addStudentRequest = new AddStudentToSystemRequest("username");
        assertEquals("username", addStudentRequest.getUserName());
    }

    @Test
    public void whenStudentAlreadyInSystemThrowsError(){
        AddStudentToSystemGateway gateway = mock(AddStudentToSystemGateway.class);
        String username = "username";
        AddStudentToSystemInteractor interactor = new AddStudentToSystemInteractor(gateway);
        Presenter presenter = mock(Presenter.class);
        //verify(presenter).reportError(ErrorResponse.EXISTING_STUDENT);
    }

}
