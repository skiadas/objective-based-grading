package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.UUID;

public interface ObjectiveGroupGradeGateway {

    Student getStudent(UUID studentId);
    Course getCourse(UUID courseId);
    Enrollment getEnrollment(UUID courseId, UUID studentID);

}
