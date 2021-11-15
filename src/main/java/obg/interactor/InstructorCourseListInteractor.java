package obg.interactor;

import obg.response.CourseListResponse;
import obg.response.ErrorResponse;
import obg.core.Interactor;
import obg.core.Request;
import obg.core.Response;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.InstructorCourseListGateway;
import obg.request.InstructorCourseListRequest;

import java.util.List;

public class InstructorCourseListInteractor implements Interactor {
    private InstructorCourseListGateway gateway;

    public InstructorCourseListInteractor(InstructorCourseListGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(Request request) {
        return handle((InstructorCourseListRequest) request);
    }

    public Response<List<Course>> handle(InstructorCourseListRequest request) {
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
