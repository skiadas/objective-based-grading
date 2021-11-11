package obg;

import java.util.UUID;

public class ViewPendingAttemptsRequest {

    UUID courseId;
    UUID instructorId;

    public ViewPendingAttemptsRequest(UUID courseId, UUID instructorId) {
        this.courseId = courseId;
        this.instructorId = instructorId;
    }
}
