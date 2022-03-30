package obg.request;

import obg.core.Request;

public class InstructorCourseListRequest implements Request {

    public final String instructorId;

    public InstructorCourseListRequest(String instructorId) {
        this.instructorId = instructorId;
    }
}
