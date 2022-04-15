package obg.request;

import java.util.UUID;

public class ViewUnmetRequirementTargetGradeRequest {
    public final String studentId;
    public final UUID courseId;
    public final String letterGrade;

    public ViewUnmetRequirementTargetGradeRequest(String studentId, UUID courseId, String letterGrade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.letterGrade = letterGrade;
    }
}
