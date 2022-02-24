package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.gateway.AssignAttemptScoreGateway;
import obg.request.AssignAttemptScoreRequest;

public class AssignAttemptScoreInteractor {

    private final AssignAttemptScoreGateway gateway;
    private final Presenter presenter;

    public AssignAttemptScoreInteractor(AssignAttemptScoreGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(AssignAttemptScoreRequest request) {
        Attempt attempt = gateway.getAttempt(request.attemptId);
        if (attempt == null) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPT);
        }else if (gateway.getInstructor(request.instructorId) == null){
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        }
    }
}
