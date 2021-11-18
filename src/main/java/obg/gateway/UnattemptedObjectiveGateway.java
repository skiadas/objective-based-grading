package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Student;

import java.util.List;
import java.util.UUID;

public interface UnattemptedObjectiveGateway {

    Student getStudent(String username);

    Course getCourse(UUID courseID);

    List<String> getUnattemptedObjectives(String student, UUID course);


    Boolean getStudentIsEnrolled(String userName, UUID courseID);
}


