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
        } else if (attempt.status == Attempt.AttemptStatus.PENDING) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPT_STATUS);
        } else if (instructor == null){
                presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else if (!isCourseInstructor(attempt, instructor)) {
            presenter.reportError(ErrorResponse.INVALID_COURSE_INSTRUCTOR);
        } else if (!isValidScore(request, attempt)) {
            presenter.reportError(ErrorResponse.INVALID_SCORE);
        } else {
            attempt.setScore(request.score);
            attempt.setStatus(Attempt.AttemptStatus.SCORED);
            presenter.presentAttempt(attempt);
        }
    }

    private boolean isValidScore(AssignAttemptScoreRequest request, Attempt attempt) {
        return attempt.getEnrollment().getEnrolledCourse().gradeBreaks.isValidScore(request.score);
    }

    private boolean isCourseInstructor(Attempt attempt, Instructor instructor) {
        return attempt.getEnrollment().getEnrolledCourse().isCourseInstructor(instructor);
    }
}
