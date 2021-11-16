package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.Gateway;
import obg.gateway.ViewPendingAttemptsGateway;
import obg.request.ViewPendingAttemptsRequest;
import org.eclipse.jetty.client.api.Response;

public class ViewPendingAttemptsInteractor {

    ViewPendingAttemptsGateway gateway;

    public ViewPendingAttemptsInteractor(ViewPendingAttemptsGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ViewPendingAttemptsRequest request, Presenter presenter) {
        if (gateway.getInstructor(request.instructorId) == null) {
            presenter.reportError(ErrorResponse.invalidInstructor());
        }
    }
}
