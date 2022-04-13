package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseId);

    Student getStudent(String userName);

    boolean objectiveInCourse(String objective, UUID courseId);

    boolean getStudentIsEnrolled(String userName, UUID courseId);

    int getAttemptNumber();

    Enrollment getEnrollment(UUID courseId, String studentId);

}
