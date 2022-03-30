package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

public interface Gateway extends ViewTargetGradeGateway, AttemptRequestGateway, InstructorCourseListGateway, StudentCourseListGateway, ViewPendingAttemptsGateway, AssignAttemptScoreGateway, CreateCourseGateway, AddStudentToCourseGateway, InstructorCanDeleteAttemptGateway,CreateNewInstructorGateway {

    Student getStudent(String username);

    void saveInstructor(Instructor instructor);

    void saveAttempt(Attempt attempt);

    void removeAttempt(Long longId);

    void clearAttempts();

    <E> void save(E o);

    void removeStudent(Enrollment enrollment1);

}
