package obg.gateway;

import obg.core.entity.Instructor;
import obg.core.entity.Student;

public interface Gateway extends ViewTargetGradeGateway, AttemptRequestGateway, InstructorCourseListGateway, StudentCourseListGateway {

    Student getStudent(String username);

    void addInstructor(Instructor instructor);
}
