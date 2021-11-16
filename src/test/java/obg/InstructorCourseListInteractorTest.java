package obg;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.InstructorCourseListGateway;
import obg.interactor.InstructorCourseListInteractor;
import obg.request.InstructorCourseListRequest;
import obg.core.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class InstructorCourseListInteractorTest {

    private InstructorCourseListRequest request;
    private InstructorCourseListInteractor interactor;
    private InstructorCourseListGateway gateway;
    private Presenter presenter;

    @Before
    public void setUp() {
        request = new InstructorCourseListRequest("instr");
        gateway = mock(InstructorCourseListGateway.class);
        interactor = new InstructorCourseListInteractor(gateway);
        presenter = mock(Presenter.class);
    }

    @Test
    public void interactorAsksGatewayForCorrectInstructorIdMockito() {
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(request.instructorId);
    }

    @Test
    public void returnErrorIfInvalidInstructorId() {
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.invalidInstructor());
    }

    @Test
    public void returnListOfCoursesThatInstructorTeaches() {
        List<Course> courses = makeCourses();
        Instructor instructor = new Instructor(request.instructorId);
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(instructor);
        when(gateway.getCoursesTaughtBy(instructor))
                .thenReturn(courses);
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(request.instructorId);
        verify(gateway).getCoursesTaughtBy(instructor);
        verify(presenter).presentInstructorCourseList(courses);
    }

    private List<Course> makeCourses() {
        Course course1 = makeCourse("course1");
        Course course2 = makeCourse("course2");
        Course course3 = makeCourse("course3");
        return List.of(course1, course2, course3);
    }

    private Course makeCourse(String courseName) {
        return new Course(UUID.randomUUID(), courseName);
    }

}
