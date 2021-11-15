package obg;

import obg.core.AppContext;
import obg.core.Interactor;
import obg.core.Request;
import obg.core.Response;
import obg.gateway.Gateway;
import obg.interactor.AttemptRequestInteractor;
import obg.interactor.InstructorCourseListInteractor;
import obg.request.AttemptRequestRequest;
import obg.request.InstructorCourseListRequest;

import java.util.UUID;

public class ConcreteAppContext implements AppContext {
    private final GatewayFactory gatewayFactory;

    public ConcreteAppContext(GatewayFactory gatewayFactory) {
        this.gatewayFactory = gatewayFactory;
    }

    public Request getInstructorCourseListRequest(String instructorId) {
        return new InstructorCourseListRequest(instructorId);
    }

    public Request getRequestAttemptRequest(String studentId, String courseId, String objective) {
        return new AttemptRequestRequest(studentId, UUID.fromString(courseId), objective);
    }

    public Interactor getInteractorFor(Request request) {
        if (request instanceof InstructorCourseListRequest) {
            return new InstructorCourseListInteractor(getGateway());
        } else if (request instanceof AttemptRequestRequest){
            return new AttemptRequestInteractor(getGateway());
        } else {
            throw new RuntimeException("No known interactor for: " + request.getClass());
        }
    }

    public Response handle(Request request) {
        if (request instanceof InstructorCourseListRequest) {
            return new InstructorCourseListInteractor(getGateway())
                    .handle((InstructorCourseListRequest) request);
        } else if (request instanceof AttemptRequestRequest){
            return new AttemptRequestInteractor(getGateway())
                    .handle((AttemptRequestRequest) request);
        } else {
            throw new RuntimeException("No known interactor for: " + request.getClass());
        }
    }

    public Gateway getGateway() {
        return gatewayFactory.acquireGateway();
    }
}
