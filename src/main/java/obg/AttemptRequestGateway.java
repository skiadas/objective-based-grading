package obg;

import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseId);

    Student getStudent(String userName);

    boolean objectiveInCourse(String objective, UUID courseID);

    boolean getStudentIsEnrolled(String userName, UUID courseID);
}
