package obg;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AttemptRequestTest {

    public UUID randID;
    private AttemptRequestRequest request;
    private AttemptRequestGateway gateway;
    private AttemptRequestInteractor interactor;
    private Course course;
    private Student student;

    @Before
    public void setUp() {
        gateway = mock(AttemptRequestGateway.class);
        randID = randomUUID();
        request = new AttemptRequestRequest("DoeJ24", randID, "L1");
        course = new Course(randID, null, null, null);
        student = new Student(null, request.userName, null);
        interactor = new AttemptRequestInteractor(gateway);
    }

    @Test
    public void InteractorAskGatewayForCorrectCourse() {
        interactor.handle(request);
        verify(gateway).getCourse(request.courseID);
    }


    @Test
    public void CheckInvalidCourseErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(null);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidCourse(), response);
    }

    @Test
    public void InteractorAskGatewayForCorrectStudent() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        interactor.handle(request);
        verify(gateway).getStudent(request.userName);
    }

    @Test
    public void checkInvalidStudentErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(null);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidStudent(), response);
    }

    @Test
    public void InteractorAskGatewayForCorrectObjective() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        interactor.handle(request);
        verify(gateway).objectiveInCourse(request.objective, request.courseID);

    }

    @Test
    public void InvalidObjectiveTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(false);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidObjective(), response);
    }

    @Test
    public void notEnrolledErrorTest() {
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.objectiveInCourse(request.objective, request.courseID)).thenReturn(true);
        when(gateway.getStudentIsEnrolled(request.userName, request.courseID)).thenReturn(false);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.notEnrolled(), response);
    }


}
