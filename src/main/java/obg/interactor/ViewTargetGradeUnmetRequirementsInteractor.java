package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.ObjectiveGroup;
import obg.core.entity.Student;
import obg.gateway.ViewUnmetRequirementTargetGradeGateway;
import obg.request.ViewUnmetRequirementTargetGradeRequest;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ViewTargetGradeUnmetRequirementsInteractor {
    private final ViewUnmetRequirementTargetGradeGateway gateway;

    public ViewTargetGradeUnmetRequirementsInteractor(ViewUnmetRequirementTargetGradeGateway gateway) {
        this.gateway = gateway;
    }
    public void handle(ViewUnmetRequirementTargetGradeRequest request, Presenter presenter) {
        Student student = gateway.getStudent(request.studentId);
        Course course = gateway.getCourse(request.courseId);
        Enrollment enrollment = gateway.getEnrollment(request.courseId,request.studentId);
        if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (student == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else if (enrollment == null) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (!course.isValidLetterGrade(request.letterGrade)) {
            presenter.reportError(ErrorResponse.INVALID_LETTER_GRADE);
        } else {
            // All inputs are now validated
            EnumMap<ObjectiveGroup, Integer> targetRequirements = course.gradeBreaks.getScore(request.letterGrade);
            List<ObjectiveGroup> unmetRequirements = new ArrayList<>();

            for (ObjectiveGroup objectiveGroup : ObjectiveGroup.values()) {
                int actualScore = enrollment.computeObjectiveGroupGrade(objectiveGroup);
                int targetScore = targetRequirements.get(objectiveGroup);
                if (actualScore < targetScore) {
                    unmetRequirements.add(objectiveGroup);
                }
            }
            presenter.presentTargetGradeUnmetRequirements(unmetRequirements);
        }
    }
}
