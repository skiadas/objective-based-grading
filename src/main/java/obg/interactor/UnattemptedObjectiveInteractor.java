package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.UnattemptedObjectiveGateway;
import obg.request.UnattemptedObjectiveRequest;

import java.util.List;

public class UnattemptedObjectiveInteractor {

    private final UnattemptedObjectiveGateway gateway;
    private final Presenter presenter;


    public UnattemptedObjectiveInteractor(UnattemptedObjectiveGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(UnattemptedObjectiveRequest request){
        if (gateway.getEnrollment(request.courseId, request.studentId ) == null) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        }
        else {
            List<String> objectiveList = gateway.getEnrollment( request.courseId, request.studentId).getUnattemptedObjectives();
            presenter.presentUnattemptedObjectives(objectiveList);
        }
    }
}
