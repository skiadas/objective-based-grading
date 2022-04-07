package obg.request;

import java.util.UUID;

public class StudentViewRemainingAttemptsRequest {

    public final String studentId;
    public final UUID courseId;

    public StudentViewRemainingAttemptsRequest(String studentId, UUID courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
