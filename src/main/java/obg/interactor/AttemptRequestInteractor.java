package obg.interactor;

import obg.core.Interactor;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.AttemptStatus;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;

public class AttemptRequestInteractor implements Interactor {

    private final AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(AttemptRequestRequest request, Presenter presenter) {
        // TODO: AttemptNumber always equal to 1 does not sound right!
        // Who should be deciding the correct attemptNumber?
        int attemptNumber = 1;
        Attempt attempt = new Attempt(request.objective, attemptNumber, request.userName, request.courseID, AttemptStatus.PENDING);
        if (gateway.getCourse(request.courseID) == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (gateway.getStudent(request.userName) == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        } else if (!gateway.getStudentIsEnrolled(request.userName, request.courseID)) {
            presenter.reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
        }
        presenter.presentAttemptCreated(attempt);
    }

}
