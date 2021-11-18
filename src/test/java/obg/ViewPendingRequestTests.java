package obg;

import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.ViewPendingAttemptsGateway;
import obg.interactor.ViewPendingAttemptsInteractor;
import obg.request.ViewPendingAttemptsRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static obg.core.ErrorResponse.*;
import static obg.core.entity.AttemptStatus.COMPLETED;
import static obg.core.entity.AttemptStatus.PENDING;
import static org.mockito.Mockito.*;

public class ViewPendingRequestTests {

    private ViewPendingAttemptsRequest request;
    private ViewPendingAttemptsGateway gateway;
    private Presenter presenter;
    private ViewPendingAttemptsInteractor interactor;
    private Instructor instructor;
    private Course course;

    @Before
    public void setUp(){
        UUID courseId = UUID.randomUUID();
        UUID instructorId = UUID.randomUUID();
        request = new ViewPendingAttemptsRequest(courseId, instructorId);
        gateway = mock(ViewPendingAttemptsGateway.class);
        presenter = mock(Presenter.class);
        interactor = new ViewPendingAttemptsInteractor(gateway);
        instructor = new Instructor("instructor");
        course = new Course(UUID.randomUUID(), "course1");
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
    public void invalidInstructorErrorResponse() {
        whenInstructor(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(INVALID_INSTRUCTOR);
    }

    @Test
    public void invalidCourseErrorResponse() {
        whenInstructor(instructor);
        whenCourse(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(INVALID_COURSE);
    }

    @Test
    public void invalidCourseInstructorErrorResponse() {
        whenInstructor(instructor);
        whenCourse(course);
        interactor.handle(request, presenter);
        verify(presenter).reportError(INVALID_COURSE_INSTRUCTOR);
    }

    @Test
    public void generatePendingAttemptsList() {
        whenInstructor(instructor);
        whenCourse(course);
        course.setInstructor(instructor);
        Attempt attempt1 = new Attempt("obj1", 1, "student1", UUID.randomUUID(), PENDING);
        Attempt attempt2 = new Attempt("obj2", 2, "student2", UUID.randomUUID(), PENDING);
        Attempt attempt3 = new Attempt("obj3", 3, "student3", UUID.randomUUID(), COMPLETED);
        List<Attempt> expectedList = new ArrayList<>(List.of(attempt1, attempt2, attempt3));
        when(gateway.getAttempts(course)).thenReturn(expectedList);
        interactor.handle(request, presenter);
        verify(presenter).presentPendingAttempts(expectedList);
    }

    private void whenCourse(Course course) {
        when(gateway.getCourse(request.courseId)).thenReturn(course);
    }

    private void whenInstructor(Instructor instructor) {
        when(gateway.getInstructor(request.instructorId)).thenReturn(instructor);
    }
}
