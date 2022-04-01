package obg.request;

import java.util.UUID;

public class ComputeObjectiveGradeRequest {

    public UUID courseId;
    public String studentId;

    public ComputeObjectiveGradeRequest(UUID courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
