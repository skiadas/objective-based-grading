package obg.request;

import java.util.UUID;

public class ObjectiveGradeRequest {

    public String userName;
    String studentId;
    public UUID courseId;

    public ObjectiveGradeRequest(String studentId, UUID courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }
}
