package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Enrollment;
import obg.gateway.DeleteObjectiveGateway;
import obg.presenter.DeleteObjectivePresenter;
import obg.request.DeleteObjectiveRequest;

public class DeleteObjectiveInteratcor {
    final private DeleteObjectiveGateway gateway;

    public DeleteObjectiveInteratcor(DeleteObjectiveGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(DeleteObjectiveRequest request, DeleteObjectivePresenter presenter) {
        Enrollment e = request.enrollment;
        String object = request.obj1;
        if (e == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (e.getAttemptMap().getAttemptList(object) == null){
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        }else{
            e.deleteObjective(object);
            gateway.deleteObjective(object, e);
            presenter.reportObjectDeletedMessage(object);
        }
    }
}
