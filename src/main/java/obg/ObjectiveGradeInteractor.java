package obg;

import obg.core.Presenter;
import obg.gateway.AttemptRequestGateway;
import obg.request.ObjectiveGradeRequest;

public class ObjectiveGradeInteractor {

    public AttemptRequestGateway gateway;

    public ObjectiveGradeInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }
    public void handle(ObjectiveGradeRequest request, Presenter presenter) {

    }
}
