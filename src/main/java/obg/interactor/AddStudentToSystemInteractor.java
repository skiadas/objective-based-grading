package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.AddStudentToSystemGateway;
import obg.request.AddStudentToSystemRequest;

public class AddStudentToSystemInteractor {
    private final AddStudentToSystemGateway gateway;
    Presenter presenter;

    public AddStudentToSystemInteractor(AddStudentToSystemGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(AddStudentToSystemRequest request) {
        if (gateway.getStudentUsername(request.userName) != null) {
            presenter.reportError(ErrorResponse.EXISTING_STUDENT);
        } else {
            gateway.addStudent(request.userName);
        }
    }
}
