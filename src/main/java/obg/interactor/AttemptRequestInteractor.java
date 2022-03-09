package obg.interactor;
import obg.core.Interactor;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;

public class AttemptRequestInteractor implements Interactor {

    private final AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(AttemptRequestRequest request, Presenter presenter) {
        Enrollment enroll = gateway.getEnrollment(request.courseID, request.studentID);
        if (gateway.getEnrolledStudent() == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if  (gateway.getEnrolledCourse() == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        }

        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enroll);
        presenter.presentAttempt(attempt);
    }

}
