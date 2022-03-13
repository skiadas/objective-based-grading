package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Instructor;
import obg.gateway.CreateCourseGateway;
import obg.presenter.CreateCoursePresenter;
import obg.request.CreateCourseRequest;

public class CreateCourseInteractor {
    private final CreateCourseGateway gateway;

    public CreateCourseInteractor(CreateCourseGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(CreateCourseRequest request, CreateCoursePresenter presenter) {
        Instructor instructor = gateway.getInstructor(request.instructorId);
        presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }
}
