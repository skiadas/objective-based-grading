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

            int scoreBasic = enrollment.computeObjectiveGroupGrade(ObjectiveGroup.BASIC);
            int scoreCore = enrollment.computeObjectiveGroupGrade(ObjectiveGroup.CORE);
            int scoreExtra = enrollment.computeObjectiveGroupGrade(ObjectiveGroup.EXTRA);

            List<ObjectiveGroup> unmetRequirements = new ArrayList<>();
            if (scoreBasic < targetRequirements.get(ObjectiveGroup.BASIC)) {
                unmetRequirements.add(ObjectiveGroup.BASIC);
            }
            if (scoreCore < targetRequirements.get(ObjectiveGroup.CORE)) {
                unmetRequirements.add(ObjectiveGroup.CORE);
            }
            if (scoreExtra < targetRequirements.get(ObjectiveGroup.EXTRA)) {
                unmetRequirements.add(ObjectiveGroup.EXTRA);
            }

            presenter.presentTargetGradeUnmetRequirements(unmetRequirements);
        }
    }
}
