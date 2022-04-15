package obg.core;

import obg.core.entity.*;
import obg.presenter.InstructorCanDeleteAttemptPresenter;
import obg.presenter.StudentViewRemainingAttemptsPresenter;
import obg.response.TargetGradeRequirementsResponse;

import java.util.List;

public interface Presenter extends InstructorCanDeleteAttemptPresenter, StudentViewRemainingAttemptsPresenter {
    void reportError(String errorMessage);

    void presentInstructorCourseList(List<Course> courses);

    void presentStudentCourseList(List<Course> courses);

    void presentAttempt(Attempt attempt);

    void presentTargetGradeRequirements(TargetGradeRequirementsResponse response);

    void presentTargetGradeUnmetRequirements(List<ObjectiveGroup> unmetRequirements);

    void presentPendingAttempts(List<Attempt> pendingAttempts);

    void presentUnattemptedObjectives(List<String> objectives);

    void presentIndexPage();

    void presentLoginScreen();

    void presentsRemovedStudent(Enrollment enrollment);

    void presentObjectiveGrade(int objGrade);

    void presentAddedStudent(Student student);
}