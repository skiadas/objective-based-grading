package obg.request;

import obg.core.Request;

import java.util.UUID;

public class AttemptRequestRequest implements Request {

    public final String studentId;
    public final UUID courseId;
    public final String objective;

    public AttemptRequestRequest(String userName, UUID courseId, String objective) {
        this.studentId = userName;
        this.courseId = courseId;
        this.objective = objective;
    }
}
