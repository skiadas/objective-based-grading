package obg.request;

import java.util.UUID;

public class ViewPendingAttemptsRequest {

    public final UUID courseId;
    public final UUID instructorId;

    public ViewPendingAttemptsRequest(UUID courseId, UUID instructorId) {
        this.courseId = courseId;
        this.instructorId = instructorId;
    }
}
