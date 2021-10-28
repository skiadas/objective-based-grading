package obg;

import java.util.UUID;

public class AttemptRequestInteractor {

    private AttemptRequestResponse attemptResponse;

    public AttemptRequestInteractor(CourseTestGateway studentGateway) {
        this.studentGateway = studentGateway;
    }

    private CourseTestGateway studentGateway;

    public Response handle(AttemptRequestRequest request){
        attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
        return getIsCourseResponse(request.courseID, studentGateway);

    }

    private Response getIsCourseResponse(UUID course, CourseTestGateway gateway) {
        Course Course1 = new Course(course,null,null,null);
        if( gateway.isValidCourse(Course1)){
            return attemptResponse;
        }
        return ErrorResponse.invalidCourseError();
    }
}
