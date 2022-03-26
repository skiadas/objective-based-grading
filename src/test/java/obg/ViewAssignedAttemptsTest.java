package obg;

import obg.core.ErrorResponse;
import obg.core.entity.*;
import obg.gateway.AssignedAttemptGateway;
import obg.interactor.ViewAssignedAttemptsInteractor;
import obg.presenter.ViewAssignedAttemptsPresenter;
import obg.request.ViewAssignedAttemtsRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class ViewAssignedAttemptsTest {

    public UUID randID;
    private ViewAssignedAttemtsRequest request;
    private AssignedAttemptGateway gateway;
    private ViewAssignedAttemptsInteractor interactor;
    private Course course;
    private Student student;
    private ViewAssignedAttemptsPresenter presenter;
    private Enrollment enrollment;
    private List<String> objectives = new ArrayList<>();

    @Before
    public void setUp() {
        presenter = mock(ViewAssignedAttemptsPresenter.class);
        gateway = mock(AssignedAttemptGateway.class);
        randID = randomUUID();
        course = new Course(randID, "courseName");
        student = new Student(randomUUID(), "userName");
        interactor = new ViewAssignedAttemptsInteractor(gateway);
        enrollment = new Enrollment(course, student, "12-15-2020", false);
        objectives.add("obj1");
        objectives.add("obj2");
        request = new ViewAssignedAttemtsRequest(student, course, objectives);
    }


    @Test
    public void canShowZeroAssignedAttempts() {
        when(gateway.getEnrollment(course, student)).thenReturn(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).presentAssignedAttempts(new AttemptList());
    }

    @Test
    public void canShowSomeAssignedAttempts() {
        Attempt attempt = new Attempt("obj1", 1, enrollment);
        Attempt attemptAssigned = new Attempt("obj2", 1, enrollment);
        attemptAssigned.setStatus(Attempt.AttemptStatus.ASSIGNED);
        AttemptList expectedList = new AttemptList();
        expectedList.add(attemptAssigned);
        enrollment.addAttempt(attempt);
        enrollment.addAttempt(attemptAssigned);
        enrollment.getObjectName();
        when(gateway.getEnrollment(course, student)).thenReturn(enrollment);
        interactor.handle(request, presenter);
        verify(presenter).presentAssignedAttempts(expectedList);
    }

    @Test
    public void canReportErrorWhenNoEnrollment() {
        when(gateway.getEnrollment(course, student)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }
}
