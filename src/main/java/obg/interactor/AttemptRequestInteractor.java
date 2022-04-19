package obg.interactor;
import obg.core.Interactor;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.Enrollment;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;

public class AttemptRequestInteractor implements Interactor {

    private final AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(AttemptRequestRequest request, Presenter presenter) {
        Enrollment enroll = gateway.getEnrollment(request.courseId, request.studentId);
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enroll);
        if (gateway.getEnrollment(request.courseId, request.studentId) == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (!enroll.course.isValidObjective(request.objective)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        } else if (attempt.getAttemptNumber() <= 0) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPTNUMBER );

        }
        gateway.getEnrollment(request.courseId,request.studentId).subtractRemainingAttempts();
        presenter.presentAttempt(attempt);
    }

    // In class: change around some of the checks to account for no negative remainingAttempts

}
