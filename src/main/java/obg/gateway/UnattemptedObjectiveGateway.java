package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.List;
import java.util.UUID;

public interface UnattemptedObjectiveGateway {

    Enrollment getEnrollment(UUID courseId, String studentId);
}


