package obg;

import obg.mocks.GatewayTestDummy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseProvidingGateway extends GatewayTestDummy {
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

    public boolean isValidCourse(Course course){
        return providedCourses.contains(course);
    }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        givenInstructor = instructor;
        return providedCourses;
    }
}
