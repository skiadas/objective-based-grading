package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.unetemptedObjectiveGateway;
import obg.request.unetemptedObjectiveRequest;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class UnetemptedObjectivesTest {

    @Test
    public void InvalidStudentTest(){
        unetemptedObjectiveGateway gateway = mock(unetemptedObjectiveGateway.class);
        unetemptedObjectiveRequest request = new unetemptedObjectiveRequest("Dave", UUID.randomUUID());
        UnetemptedObjectiveInteractor Interactor = new UnetemptedObjectiveInteractor(gateway);
        Presenter presenter = mock(Presenter.class);
        Interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);

    }
}
