package obg;

import obg.core.Presenter;
import obg.core.entity.*;
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
import static obg.core.entity.Attempt.AttemptStatus.COMPLETED;
import static obg.core.entity.Attempt.AttemptStatus.PENDING;
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
        String instructorId = "instructor";
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
        String instructorId = "instructor";
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
        List<Attempt> expectedList = generateListOfAttempts();
        when(gateway.getAttempts(course)).thenReturn(expectedList);
        interactor.handle(request, presenter);
        verify(presenter).presentPendingAttempts(expectedList);
    }

    private List<Attempt> generateListOfAttempts() {
        Attempt attempt1 = createAttempt("obj1", 1, makeStudent("student1"), course, PENDING);
        Attempt attempt2 = createAttempt("obj2", 2, makeStudent("student2"), course, PENDING);
        Attempt attempt3 = createAttempt("obj3", 3, makeStudent("student3"), course, COMPLETED);
        return new ArrayList<>(List.of(attempt1, attempt2, attempt3));
    }

    private Student makeStudent(String name) {
        return new Student(UUID.randomUUID(), name);
    }

    private Attempt createAttempt(String objective, int attemptNum, Student student, Course course, Attempt.AttemptStatus status) {
        Attempt attempt = new Attempt(objective, attemptNum, new Enrollment(course, student));
        attempt.setStatus(status);
        return attempt;
    }

    private void whenCourse(Course course) {
        when(gateway.getCourse(request.courseId)).thenReturn(course);
    }

    private void whenInstructor(Instructor instructor) {
        when(gateway.getInstructor(request.instructorId)).thenReturn(instructor);
    }
}
