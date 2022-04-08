package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.ObjectiveGroup;
import obg.core.entity.Student;

import java.util.List;
import java.util.UUID;

public interface ObjectiveGroupGradeGateway {

    Student getStudent(UUID studentId);
    Course getCourse(UUID courseId);
    Enrollment getEnrollment(UUID courseId, UUID studentID);
    List<Integer> getHighestObjectiveGrade(UUID studentId, UUID courseId, ObjectiveGroup group);

}
