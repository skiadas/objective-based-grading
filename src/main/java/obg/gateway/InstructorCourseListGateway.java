package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Instructor;

import java.util.List;

public interface InstructorCourseListGateway {
    Instructor getInstructor(String instructorId);

    List<Course> getCoursesTaughtBy(Instructor instructor);
}
