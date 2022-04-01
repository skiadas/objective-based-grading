package obg.request;

import java.util.UUID;

public class ComputeObjectiveGradeRequest {

    public UUID courseId;
    public String studentId;
    public String obj;

    public ComputeObjectiveGradeRequest(UUID courseId, String studentId, String obj) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.obj = obj;
    }
}
