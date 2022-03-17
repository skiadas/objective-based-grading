package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

public interface AssignedAttemptGateway {
    Enrollment getEnrollment(Course course, Student student);
}
