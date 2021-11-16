package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.ViewPendingAttemptsGateway;
import obg.interactor.ViewPendingAttemptsInteractor;
import obg.request.ViewPendingAttemptsRequest;
import obg.response.ViewPendingAttemptsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class ViewPendingRequestTests {

    private ViewPendingAttemptsRequest request;
    private ViewPendingAttemptsGateway gateway;
    private Presenter presenter;
    private ViewPendingAttemptsInteractor interactor;

    @Before
    public void setUp(){
        UUID courseId = UUID.randomUUID();
        UUID instructorId = UUID.randomUUID();
        request = new ViewPendingAttemptsRequest(courseId, instructorId);
        gateway = mock(ViewPendingAttemptsGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ViewPendingAttemptsInteractor(gateway);
    }

    @Test
    public void canCreateRequest() {
        UUID courseId = UUID.randomUUID();
        UUID instructorId = UUID.randomUUID();
        ViewPendingAttemptsRequest request = new ViewPendingAttemptsRequest(courseId, instructorId);
        assertEquals(courseId, request.courseId);
        assertEquals(instructorId, request.instructorId);
    }

    @Test
    public void canCreateResponse() {
        UUID courseId = UUID.randomUUID();
        Course course = new Course(courseId, "course");
        ViewPendingAttemptsResponse response = new ViewPendingAttemptsResponse(course);
        assertEquals(course, response.course);
        assertTrue(response.objectiveStatuses.isEmpty());
        assertTrue(response.studentObjectives.isEmpty());
    }

    @Test
    public void invalidInstructorErrorResponse() {
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }

    @Test
    public void invalidCourseErrorResponse() {
        Instructor instructor = new Instructor("Skiadas");
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(instructor);
        when(gateway.getCourse(request.courseId)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void invalidCourseInstructorErrorResponse() {
        Instructor instructor = new Instructor("Skiadas");
        Course course = new Course(UUID.randomUUID(), "CS 321");
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(instructor);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE_INSTRUCTOR);
    }
}
