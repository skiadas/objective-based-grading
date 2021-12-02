package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseID);

    Student getStudent(String userName);

    boolean objectiveInCourse(String objective, UUID courseID);

    boolean getStudentIsEnrolled(String userName, UUID courseID);

    int getAttemptNumber();

    HashMap getObjMap(String studentName, UUID courseID);
}
