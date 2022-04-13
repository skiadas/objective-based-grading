package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Student;
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
            Student student = new Student(request.userName);
            gateway.addStudent(student);
            presenter.presentAddedStudent(student);
        }
    }
}
