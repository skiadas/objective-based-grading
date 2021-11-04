package obg;

import java.util.List;

public interface InstructorCourseListGateway {
    Instructor getInstructor(String instructorId);

    List<Course> getCoursesTaughtBy(Instructor instructor);
}
