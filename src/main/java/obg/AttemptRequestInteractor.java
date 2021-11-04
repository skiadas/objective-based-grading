package obg;

import java.util.ArrayList;
import java.util.UUID;

public class AttemptRequestInteractor {

    private AttemptRequestResponse attemptResponse;

    private AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(AttemptRequestRequest request){
        attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
        return getIsCourseResponse(request, gateway);

    }

    private Response getIsCourseResponse(AttemptRequestRequest request, AttemptRequestGateway gateway) {
        Student student1 = new Student(null, request.userName, null);
        Course course = new Course(request.courseID, null, null, null);

        if(gateway.getCourse(request.courseID) == null){ return ErrorResponse.invalidCourse(); }
        else if(gateway.getStudent(student1) == null) { return ErrorResponse.invalidStudent(); }
        else if(!gateway.objectiveInCourse(request.objective, course)) { return ErrorResponse.invalidObjective(); }
        else if(!gateway.getStudentIsEnrolled(student1, course)) { return ErrorResponse.notEnrolled(); }

        return attemptResponse;
    }
}
