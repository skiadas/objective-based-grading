package obg;

import java.util.UUID;

public class AttemptRequestInteractor {

    private AttemptRequestResponse attemptResponse;

    public AttemptRequestInteractor(Gateway studentGateway) {
        this.studentGateway = studentGateway;
    }

    private Gateway studentGateway;

    public Response handle(AttemptRequestRequest request){
        attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
        return getIsCourseResponse(request, studentGateway);

    }

    private Response getIsCourseResponse(AttemptRequestRequest request, Gateway gateway) {
        Course course1 = new Course(request.courseID,null,null,null);
        Student student1 = new Student(null, request.userName, null);
        if(!gateway.isValidCourse(course1)){
            return ErrorResponse.invalidCourseError();
        }
        else if(!gateway.isValidStudent(student1)) {
            return ErrorResponse.invalidStudentError();
        }

        return attemptResponse;
    }
}
