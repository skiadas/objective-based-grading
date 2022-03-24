package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Interactor;
import obg.core.Presenter;
import obg.gateway.RemoveStudentGateway;
import obg.request.RemoveStudentRequest;

public class RemoveStudentInteractor implements Interactor {

    private final RemoveStudentGateway gateway;

    public RemoveStudentInteractor(RemoveStudentGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(RemoveStudentRequest request, Presenter presenter) {
        if(gateway.getEnrollment(request.enrollment) == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        }
        presenter.presentsRemovedStudent(request.enrollment);
    }
}
