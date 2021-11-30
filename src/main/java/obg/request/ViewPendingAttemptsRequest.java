package obg.request;

import java.util.UUID;

public class ViewPendingAttemptsRequest {

    public final UUID courseId;
    public final String instructorId;

    public ViewPendingAttemptsRequest(UUID courseId, String instructorId) {
        this.courseId = courseId;
        this.instructorId = instructorId;
    }
}
