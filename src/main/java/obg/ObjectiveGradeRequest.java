package obg;

import java.util.UUID;

public class ObjectiveGradeRequest {

    String studentId;
    UUID courseId;

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
