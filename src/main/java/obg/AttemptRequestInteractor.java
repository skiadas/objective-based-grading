package obg;

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
        Course course1 = new Course(request.courseID,null,null,null);
        Student student1 = new Student(null, request.userName, null);
        if(!gateway.isValidCourse(course1)){
            return ErrorResponse.invalidCourse();
        }
        else if(!gateway.isValidStudent(student1)) {
            return ErrorResponse.invalidStudent();
        }
        else if(!gateway.isValidObjective(request.objective)) {
            return ErrorResponse.invalidObjective();
        }
        return attemptResponse;
    }
}
