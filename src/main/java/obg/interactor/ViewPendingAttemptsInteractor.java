package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.ViewPendingAttemptsGateway;
import obg.request.ViewPendingAttemptsRequest;

public class ViewPendingAttemptsInteractor {

    ViewPendingAttemptsGateway gateway;

    public ViewPendingAttemptsInteractor(ViewPendingAttemptsGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ViewPendingAttemptsRequest request, Presenter presenter) {
        if (gateway.getInstructor(request.instructorId) == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        }
        if (gateway.getCourse(request.courseId) == null){
            final ErrorResponse response = new ErrorResponse(ErrorResponse.INVALID_COURSE);
            presenter.reportError(response.getErrorMessage());
        }
    }
}
