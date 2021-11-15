package obg.request;

import java.util.UUID;

public class ViewPendingAttemptsRequest {

    public UUID courseId;
    public UUID instructorId;

    public ViewPendingAttemptsRequest(UUID courseId, UUID instructorId) {
        this.courseId = courseId;
        this.instructorId = instructorId;
    }
}
