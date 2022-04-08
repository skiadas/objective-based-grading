package obg.gateway;

import obg.core.entity.*;

public interface Gateway extends ViewTargetGradeGateway, AttemptRequestGateway, InstructorCourseListGateway, StudentCourseListGateway, ViewPendingAttemptsGateway, AssignAttemptScoreGateway, CreateCourseGateway, AddStudentToCourseGateway, InstructorCanDeleteAttemptGateway, CreateNewInstructorGateway, RemoveStudentGateway, StudentViewRemainingAttemptsGateway, AddStudentToSystemGateway {

    Student getStudent(String username);

    void saveInstructor(Instructor instructor);

    void saveAttempt(Attempt attempt);

    void removeAttempt(Long longId);

    <E> void save(E o);

    void removeStudent(Enrollment enrollment1);

    void saveCourse(Course course);

    void assignCourseInstructor(Course course, Instructor instructor);

    void addCourse(Course course);
}
