package obg;

import obg.mocks.GatewayTestDummy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class InstructorCourseListInteractorTest {

    private UUID instructorId;
    private InstructorCourseListRequest request;
    private InstructorCourseListInteractor interactor;

    @Before
    public void setUp() {
        instructorId = UUID.randomUUID();
        request = new InstructorCourseListRequest(instructorId);
    }

    @Test
    public void interactorAsksGatewayForCorrectInstructorId() {
        InvalidInstructorGateway gateway = new InvalidInstructorGateway();
        interactor = new InstructorCourseListInteractor(gateway);
        interactor.handle(request);
        assertEquals(instructorId, gateway.givenInstructorId);
    }

    @Test
    public void returnErrorIfInvalidInstructorId() {
        InstructorCourseListGateway gateway = new InvalidInstructorGateway();
        interactor = new InstructorCourseListInteractor(gateway);
        Response response = interactor.handle(request);
        assertEquals(ErrorResponse.invalidInstructor(), response);
    }

    @Test
    public void returnListOfCoursesThatInstructorTeaches() {
        List<Course> courses = makeCourses();
        CourseProvidingGateway gateway = new CourseProvidingGateway(courses);
        interactor = new InstructorCourseListInteractor(gateway);
        Response response = interactor.handle(request);
        // TODO: Need better way here
        assertEquals(gateway.instructor, gateway.givenInstructor);
        assertEquals(new CourseListResponse(courses), response);

    }

    private List<Course> makeCourses() {
        Course course1 = makeCourse("course1");
        Course course2 = makeCourse("course2");
        Course course3 = makeCourse("course3");
        List<Course> courses = List.of(course1, course2, course3);
        return courses;
    }

    private Course makeCourse(String courseName) {
        return new Course(UUID.randomUUID(), courseName);
    }

    private static class InvalidInstructorGateway implements InstructorCourseListGateway {
        UUID givenInstructorId;

        public Instructor getInstructor(UUID instructorId) {
            givenInstructorId = instructorId;
            return null;
        }

        public List<Course> getCoursesTaughtBy(Instructor instructor) {
            return null;
        }
    }

    private static class CourseProvidingGateway implements InstructorCourseListGateway {
        private List<Course> providedCourses;
        public Instructor instructor;
        public Instructor givenInstructor;

        public CourseProvidingGateway(List<Course> courses) {
            providedCourses = new ArrayList<>(courses);
        }

        public Instructor getInstructor(UUID instructorId) {
            instructor = new Instructor(instructorId);
            return instructor;
        }

        public List<Course> getCoursesTaughtBy(Instructor instructor) {
            givenInstructor = instructor;
            return providedCourses;
        }
    }
}
