package obg;

import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseId);

    Student getStudent(Student student);

    boolean objectiveInCourse(String objective, Course course);

    boolean getStudentIsEnrolled(Student student1, Course course);
}
