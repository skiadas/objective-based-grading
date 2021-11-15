package obg;

import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.mocks.GatewayTestDummy;

import java.util.ArrayList;
import java.util.List;

public class CourseProvidingGateway extends GatewayTestDummy {
    private List<Course> providedCourses;
    public Instructor instructor;
    public Instructor givenInstructor;

    public CourseProvidingGateway(List<Course> courses) {
        providedCourses = new ArrayList<>(courses);
    }

    public Instructor getInstructor(String instructorId) {
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
