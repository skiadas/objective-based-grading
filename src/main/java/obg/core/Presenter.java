package obg.core;

import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Student;
import obg.response.TargetGradeRequirementsResponse;

import java.util.ArrayList;
import java.util.List;

public interface Presenter {
    void reportError(String errorMessage);

    void presentInstructorCourseList(List<Course> courses);

    void presentStudentCourseList(List<Course> courses);

    void presentAttemptCreated(Attempt attempt);

    void presentTargetGradeRequirements(TargetGradeRequirementsResponse response);

    void presentPendingAttempts();

    void presentUnattemptedObjectives(List<String> objectives);
}
