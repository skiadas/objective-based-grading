package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Instructor;
import obg.gateway.AssignAttemptScoreGateway;
import obg.request.AssignAttemptScoreRequest;

import java.util.UUID;

public class AssignAttemptScoreInteractor {

    private final AssignAttemptScoreGateway gateway;
    private final Presenter presenter;

    public AssignAttemptScoreInteractor(AssignAttemptScoreGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(AssignAttemptScoreRequest request) {
        Attempt attempt = gateway.getAttempt(UUID.fromString(request.attemptId));
        Instructor instructor = gateway.getInstructor(request.instructorId);
        if (attempt == null) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPT);
        } else if (instructor == null){
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else if (attempt.isInGradableStatus()) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPT_STATUS);
        } else if (!attempt.isEnrollmentCourseInstructor(instructor)) {
            presenter.reportError(ErrorResponse.NOT_COURSE_INSTRUCTOR);
        } else if (!attempt.isValidScore(request.score)) {
            presenter.reportError(ErrorResponse.INVALID_SCORE);
        } else {
            attempt.assignScore(request.score);
            presenter.presentAttempt(attempt);
        }
    }

}
