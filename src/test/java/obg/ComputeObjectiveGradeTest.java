package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.ComputeObjectiveGradeGateway;
import obg.interactor.ComputeObjectiveGradeInteractor;
import obg.request.ComputeObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ComputeObjectiveGradeTest {

    private UUID courseId;
    private ComputeObjectiveGradeGateway gateway;
    private Presenter presenter;
    private ComputeObjectiveGradeInteractor interactor;
    private ComputeObjectiveGradeRequest request;
    private Enrollment enroll;
    private UUID studentId;
    private Attempt attempt;

    @Before
    public void setUp() throws Exception {
        courseId = UUID.randomUUID();
        studentId = UUID.randomUUID();
        request = new ComputeObjectiveGradeRequest(courseId, "joe", "obj1");
        gateway = mock(ComputeObjectiveGradeGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ComputeObjectiveGradeInteractor(gateway, presenter);
        enroll = new Enrollment(new Course(request.courseId, "course"), new Student(studentId, request.studentId));
        attempt = new Attempt("obj1", 1, enroll);
    }

    @Test
    public void invalidEnrollment() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(null);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void computeGradeWithZeroAttempts() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void computeGradeWithOneAttemptGivenScoreFive() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        attempt.assignScore(5);
        enroll.addAttempt(attempt);
        when(gateway.getAttempts(request.obj,1)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(attempt.getScore());
    }

    @Test
    public void computeGradeWithThreeAttempts() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        Attempt attempt2 = new Attempt("obj1", 2, enroll);
        Attempt attempt3 = new Attempt("obj1", 3, enroll);
        attempt.assignScore(2);
        attempt2.assignScore(6);
        attempt3.assignScore(4);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        enroll.addAttempt(attempt3);
        when(gateway.getAttempts(request.obj,3)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(6);
    }

    @Test
    public void computeGradeWithThreeAttemptsOfSameScore() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        Attempt attempt2 = new Attempt("obj1", 2, enroll);
        Attempt attempt3 = new Attempt("obj1", 3, enroll);
        attempt.assignScore(2);
        attempt2.assignScore(2);
        attempt3.assignScore(2);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        enroll.addAttempt(attempt3);
        when(gateway.getAttempts(request.obj,3)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(2);
    }

    @Test
    public void computeGradeWithThreeAttemptsOfDifferentObjectives() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        Attempt attempt2 = new Attempt("obj2", 1, enroll);
        Attempt attempt3 = new Attempt("obj3", 1, enroll);
        attempt.assignScore(5);
        attempt2.assignScore(2);
        attempt3.assignScore(6);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        enroll.addAttempt(attempt3);
        when(gateway.getAttempts(request.obj,1)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        when(gateway.getAttempts(request.obj,1)).thenReturn(enroll.attemptMap.getAttemptList("obj2"));
        when(gateway.getAttempts(request.obj,1)).thenReturn(enroll.attemptMap.getAttemptList("obj3"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(attempt.getScore());
    }

    @Test
    public void computeGradeWithOnlyOneObj1Attempt() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        Attempt attempt2 = new Attempt("obj2", 1, enroll);
        Attempt attempt3 = new Attempt("obj2", 2, enroll);
        attempt.assignScore(1);
        attempt2.assignScore(7);
        attempt3.assignScore(7);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        enroll.addAttempt(attempt2);
        when(gateway.getAttempts(request.obj,1)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        when(gateway.getAttempts(request.obj,2)).thenReturn(enroll.attemptMap.getAttemptList("obj2"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(attempt.getScore());
    }

    @Test
    public void computeGradeWithLowLastAttemptScore() {
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        Attempt attempt2 = new Attempt("obj1", 2, enroll);
        Attempt attempt3 = new Attempt("obj1", 3, enroll);
        attempt.assignScore(5);
        attempt2.assignScore(5);
        attempt3.assignScore(1);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        enroll.addAttempt(attempt3);
        when(gateway.getAttempts(request.obj,3)).thenReturn(enroll.attemptMap.getAttemptList("obj1"));
        interactor.handle(request);
        verify(presenter).presentObjectiveGrade(5);
    }

}
