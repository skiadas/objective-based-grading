package obg;

import java.util.List;
import java.util.UUID;

public interface Gateway {
    Course getCourse(UUID courseId);

    boolean isValidLetterGrade(String letterGrade);

    Instructor getInstructor(UUID instructorId);

    List<Course> getCoursesTaughtBy(Instructor instructor);
}
