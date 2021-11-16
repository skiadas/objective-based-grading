package obg.interactor;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.gateway.ViewTargetGradeGateway;
import obg.request.ViewTargetGradeRequest;
import obg.core.ErrorResponse;
import obg.response.TargetGradeRequirementsResponse;

public class ViewTargetGradeInteractor {
    private final ViewTargetGradeGateway gateway;

    public ViewTargetGradeInteractor(ViewTargetGradeGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ViewTargetGradeRequest request, Presenter presenter) {
        Course course = gateway.getCourse(request.courseId);
        if (course == null) {
            final ErrorResponse response = new ErrorResponse(ErrorResponse.INVALID_COURSE);
            presenter.reportError(response.getErrorMessage());
        } else if (!course.isValidLetterGrade(request.letterGrade)) {
            final ErrorResponse response = new ErrorResponse(ErrorResponse.INVALID_LETTER_GRADE);
            presenter.reportError(response.getErrorMessage());
        } else {
            presenter.presentTargetGradeRequirements(
                    new TargetGradeRequirementsResponse(request.letterGrade,
                                                        course.gradeBreaks.get(request.letterGrade))
                                                    );
        }
    }
}
