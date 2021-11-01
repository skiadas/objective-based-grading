package obg;

import java.util.List;

public class InstructorCourseListInteractor {
    private InstructorCourseListGateway gateway;

    public InstructorCourseListInteractor(InstructorCourseListGateway gateway) {
        this.gateway = gateway;
    }

    Response handle(InstructorCourseListRequest request) {
        // Ask the gateway if instructor exists
        // TODO: Can we do this better?
        Instructor instr = gateway.getInstructor(request.instructorId);
        if (instr == null) {
            return ErrorResponse.invalidInstructor();
        }
        List<Course> courses = gateway.getCoursesTaughtBy(instr);
        return new CourseListResponse(courses);
    }
}
