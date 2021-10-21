package obg;

import java.util.UUID;

public class ViewTargetGradeRequest {

    public final String studentId;
    public final UUID courseId;
    public final String grade;

    public ViewTargetGradeRequest(String studentId, UUID courseId, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }
}
