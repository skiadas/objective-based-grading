package obg;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Student;
import obg.gateway.AttemptRequestGateway;
import obg.interactor.AttemptRequestInteractor;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class AttemptRequestTest {

    public UUID randID;
    private AttemptRequestRequest request;
    private AttemptRequestGateway gateway;
    private AttemptRequestInteractor interactor;
    private Course course;
    private Student student;
    private Presenter presenter;

    @Before
    public void setUp() {
        presenter = mock(Presenter.class);
        gateway = mock(AttemptRequestGateway.class);
        randID = randomUUID();
        request = new AttemptRequestRequest("DoeJ24", randID, "L1");
        course = new Course(randID, null, null, null);
        student = new Student(null, request.userName, null);
        interactor = new AttemptRequestInteractor(gateway);
    }

    @Test
    public void InteractorAskGatewayForCorrectCourse() {
        interactor.handle(request, presenter);
        verify(gateway).getCourse(request.courseID);
    }


    @Test
    public void CheckInvalidCourseErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.invalidCourse());
    }

    @Test
    public void InteractorAskGatewayForCorrectStudent() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        interactor.handle(request, presenter);
        verify(gateway).getStudent(request.userName);
    }

    @Test
    public void checkInvalidStudentErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.invalidStudent());
    }

    @Test
    public void InteractorAskGatewayForCorrectObjective() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        interactor.handle(request, presenter);
        verify(gateway).objectiveInCourse(request.objective, request.courseID);
    }

    @Test
    public void InvalidObjectiveTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.invalidObjective());
    }

    @Test
    public void notEnrolledErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.userName, request.courseID)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.notEnrolled());
    }

    // TODO: Should verify presenter method is called for created attempt
}
