package obg;

import java.util.UUID;

public class ViewTargetGradeRequest {

    public final String studentId;
    public final UUID courseId = UUID.randomUUID();
    public final String grade;

    public ViewTargetGradeRequest(String studentId, String grade) {
        this.studentId = studentId;
        this.grade = grade;
    }
}
