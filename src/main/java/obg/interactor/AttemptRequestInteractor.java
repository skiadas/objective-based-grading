package obg.interactor;

import obg.response.AttemptRequestResponse;
import obg.response.ErrorResponse;
import obg.core.Interactor;
import obg.core.Request;
import obg.core.Response;
import obg.core.entity.Attempt;
import obg.core.entity.AttemptStatus;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;

public class AttemptRequestInteractor implements Interactor {

    private AttemptRequestResponse attemptResponse;

    private AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(Request request) {
        return handle((AttemptRequestRequest) request);
    }

    public Response handle(AttemptRequestRequest request) {
        int attemptNumber = 1;
        Attempt attempt = new Attempt(request.objective, attemptNumber, request.userName, request.courseID, AttemptStatus.PENDING);
        attemptResponse = new AttemptRequestResponse(attempt);
        if (gateway.getCourse(request.courseID) == null) {
            return ErrorResponse.invalidCourse();
        } else if (gateway.getStudent(request.userName) == null) {
            return ErrorResponse.invalidStudent();
        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
            return ErrorResponse.invalidObjective();
        } else if (!gateway.getStudentIsEnrolled(request.userName, request.courseID)) {
            return ErrorResponse.notEnrolled();
        }
        return attemptResponse;

    }
}
