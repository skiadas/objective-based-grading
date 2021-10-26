package obg;

import java.util.UUID;

public class ViewTargetGradeRequest {

    public final UUID courseId;
    public final String grade;

    public ViewTargetGradeRequest(UUID courseId, String letterGrade) {
        this.courseId = courseId;
        this.grade = letterGrade;
    }
}
