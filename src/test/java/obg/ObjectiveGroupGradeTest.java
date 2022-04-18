package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.ObjectiveGroupGradeGateway;
import obg.interactor.ObjectiveGroupGradeInteractor;
import obg.request.ObjectiveGroupGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ObjectiveGroupGradeTest {

    private ObjectiveGroupGradeRequest request;
    private ObjectiveGroupGradeInteractor interactor;
    private ObjectiveGroupGradeGateway gateway;
    private Presenter presenter;
    private Enrollment enroll;
    private Attempt attempt1;
    private Attempt attempt2;
    private int objGroupGrade;

    @Before
    public void setUp() {
        request = new ObjectiveGroupGradeRequest(UUID.randomUUID(), "Joe", ObjectiveGroup.BASIC);
        gateway = mock(ObjectiveGroupGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ObjectiveGroupGradeInteractor(gateway, presenter);
        enroll = new Enrollment(new Course(request.courseID, "course1"), new Student(UUID.randomUUID(), request.studentID));
        enroll.getCourse().addObjective(ObjectiveGroup.BASIC,"S1");
        enroll.getCourse().addObjective(ObjectiveGroup.BASIC, "S2");
        attempt1 = new Attempt("S1", 1, enroll);
        attempt2 = new Attempt("S2", 1, enroll);
        enroll.addAttempt(attempt1);
        enroll.addAttempt(attempt2);
        attempt1.assignScore(2);
        attempt2.assignScore(3);
        objGroupGrade = 2;
    }

    @Test
    public void InvalidEnrollmentTest(){
        when(gateway.getEnrollment(request.courseID, request.studentID)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void ReturnObjectiveGroupGrade(){
        when(gateway.getEnrollment(request.courseID, request.studentID)).thenReturn(enroll);
        interactor.handle(request);
        verify(presenter).presentObjectiveGroupGrade(objGroupGrade);
    }

}
