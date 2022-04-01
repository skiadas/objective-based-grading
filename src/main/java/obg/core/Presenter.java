package obg.core;

import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.presenter.InstructorCanDeleteAttemptPresenter;
import obg.response.TargetGradeRequirementsResponse;

import java.util.List;

public interface Presenter extends InstructorCanDeleteAttemptPresenter {
    void reportError(String errorMessage);

    void presentInstructorCourseList(List<Course> courses);

    void presentStudentCourseList(List<Course> courses);

    void presentAttempt(Attempt attempt);

    void presentTargetGradeRequirements(TargetGradeRequirementsResponse response);

    void presentPendingAttempts(List<Attempt> pendingAttempts);

    void presentUnattemptedObjectives(List<String> objectives);

    void presentIndexPage();

    void presentLoginScreen();

    void presentsRemovedStudent(Enrollment enrollment);

    void presentObjectiveGrade(int objGrade);
}