package obg;

import java.util.UUID;

public class AttemptRequestInteractor {

    private AttemptRequestResponse attemptResponse;

    private AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(AttemptRequestRequest request) {
        attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
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
