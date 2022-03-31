package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.List;
import java.util.UUID;

public interface UnattemptedObjectiveGateway {

    Student getStudent(String studentId);

    Course getCourse(UUID courseId);

    List<String> getUnattemptedObjectives(String student, UUID course);

    Boolean getStudentIsEnrolled(String userName, UUID courseId);

    Enrollment getEnrollment(UUID courseId, String studentId);
}


