package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

public interface Gateway extends ViewTargetGradeGateway, AttemptRequestGateway, InstructorCourseListGateway, StudentCourseListGateway, ViewPendingAttemptsGateway, AssignAttemptScoreGateway, CreateCourseGateway {

    Student getStudent(String username);

    void addInstructor(Instructor instructor);

    void addAttempt(Attempt attempt);

    void removeAttempt(Attempt attempt);

    void clearAttempts();

    <E> void save(E o);
}
