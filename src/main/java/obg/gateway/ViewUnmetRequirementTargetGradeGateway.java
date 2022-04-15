package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.UUID;

public interface ViewUnmetRequirementTargetGradeGateway {
    Student getStudent(String studentId);
    Course getCourse(UUID courseId);
    Enrollment getEnrollment(UUID courseId, String studentId);
}
