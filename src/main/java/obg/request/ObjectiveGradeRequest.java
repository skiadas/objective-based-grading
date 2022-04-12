package obg.request;

import obg.core.entity.ObjectiveGroup;

import java.util.UUID;

public class ObjectiveGradeRequest {
    public String userName;
    public String studentId;
    public UUID courseId;

    public ObjectiveGradeRequest(String studentId, UUID courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
