package obg;

import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseId);

    boolean isValidCourse(Course course1);

    boolean isValidStudent(Student student);

    boolean isValidObjective(String objective);
}
