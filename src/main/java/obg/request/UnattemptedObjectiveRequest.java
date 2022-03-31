package obg.request;

import java.util.UUID;

public class UnattemptedObjectiveRequest {

    public String studentId;
    public UUID courseId;

    public UnattemptedObjectiveRequest(String studentId, UUID courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }


}
