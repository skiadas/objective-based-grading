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
        ArrayList<String> objectives = new ArrayList<>();
        Student student1 = new Student(null, request.userName, null);
        Course course = new Course(request.courseID, null, null, objectives);

        if(gateway.getCourse(request.courseID) == null){ return ErrorResponse.invalidCourse(); }
        else if(gateway.getStudent(student1) == null) { return ErrorResponse.invalidStudent(); }
        else if(!course.isValidObjective(request.objective)) { return ErrorResponse.invalidObjective(); }

        return attemptResponse;
    }
}
