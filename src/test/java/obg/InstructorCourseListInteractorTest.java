package obg;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InstructorCourseListInteractorTest {

    private InstructorCourseListRequest request;
    private InstructorCourseListInteractor interactor;
    private InstructorCourseListGateway gateway;

    @Before
    public void setUp() {
        request = new InstructorCourseListRequest("instr");
        gateway = mock(InstructorCourseListGateway.class);
        interactor = new InstructorCourseListInteractor(gateway);
    }

    @Test
    public void interactorAsksGatewayForCorrectInstructorIdMockito() {
        interactor.handle(request);
        verify(gateway).getInstructor(request.instructorId);
    }

    @Test
    public void returnErrorIfInvalidInstructorId() {
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(null);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidInstructor(), response);
    }

    @Test
    public void returnListOfCoursesThatInstructorTeaches() {
        List<Course> courses = makeCourses();
        Instructor instructor = new Instructor(request.instructorId);
        when(gateway.getInstructor(request.instructorId))
                .thenReturn(instructor);
        when(gateway.getCoursesTaughtBy(instructor))
                .thenReturn(courses);
        Response response = interactor.handle(request);
        verify(gateway).getInstructor(request.instructorId);
        verify(gateway).getCoursesTaughtBy(instructor);
        assertEquals(new CourseListResponse(courses), response);

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
