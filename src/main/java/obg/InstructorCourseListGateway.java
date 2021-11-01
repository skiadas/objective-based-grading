package obg;

import java.util.List;
import java.util.UUID;

public interface InstructorCourseListGateway {
    Instructor getInstructor(UUID instructorId);

    List<Course> getCoursesTaughtBy(Instructor instructor);
}
