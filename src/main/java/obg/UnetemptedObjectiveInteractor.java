package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.unetemptedObjectiveGateway;
import obg.request.unetemptedObjectiveRequest;

public class UnetemptedObjectiveInteractor {

    private final unetemptedObjectiveGateway gateway;

    public UnetemptedObjectiveInteractor(unetemptedObjectiveGateway gateway) {
        this.gateway = gateway;

    }

    public void handle(unetemptedObjectiveRequest request, Presenter presenter){
        if(gateway.getStudent(request.username) == null){
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        }

    }
}
