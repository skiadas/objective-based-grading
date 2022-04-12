package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.DeleteObjectiveGateway;
import obg.interactor.DeleteObjectiveInteratcor;
import obg.presenter.DeleteObjectivePresenter;
import obg.request.DeleteObjectiveRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DeleteObjectiveTests {
    public UUID randId;
    private Course course;
    private Student student;
    private DeleteObjectiveInteratcor interactor;
    private DeleteObjectiveGateway gateway;
    private DeleteObjectiveRequest request;
    private DeleteObjectivePresenter presenter;
    private Enrollment enrollment;
    private Attempt attempt;
    private String obj1;


    @Before
    public void setUp() {
        obj1 = "obj1";
        presenter = mock(DeleteObjectivePresenter.class);
        gateway = mock(DeleteObjectiveGateway.class);
        randId = UUID.randomUUID();
        course = new Course(randId, null, null);
        student = new Student(randId, "student1");
        interactor = new DeleteObjectiveInteratcor(gateway);
        enrollment = new Enrollment(course, student);
        attempt = new Attempt(obj1, 1, enrollment);
        enrollment.addAttempt(attempt);
        request = new DeleteObjectiveRequest(obj1, enrollment);
    }

    @Test
    public void InvalidEnrollment(){
        DeleteObjectiveRequest request1 = new DeleteObjectiveRequest(obj1, null);
        interactor.handle(request1, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }
    @Test
    public void EnrollmentValidButObjectInvaild(){
        DeleteObjectiveRequest request1 = new DeleteObjectiveRequest("obj2", enrollment);
        interactor.handle(request1, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_OBJECTIVE);
    }

    @Test
    public void deleteOneObjectiveGetDeleteMessage() {
        when(gateway.deleteObjective(obj1, enrollment)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportObjectDeletedMessage(obj1);
        assertTrue(enrollment.getAttemptMap().isEmpty());
        Attempt attemp2 = new Attempt("obj2", 1, enrollment);
        enrollment.addAttempt(attemp2);
        assertFalse(enrollment.getAttemptMap().isEmpty());
    }
}
