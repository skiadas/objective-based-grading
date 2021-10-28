package obg;

import java.util.UUID;

public class InstructorCourseListRequest {
    public final UUID instructorId;

    public InstructorCourseListRequest(UUID instructorId) {
        this.instructorId = instructorId;
    }
}
