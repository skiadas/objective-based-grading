package obg.request;

import java.util.UUID;

public class ViewTargetGradeRequest {

    public final UUID courseId;
    public final String letterGrade;

    public ViewTargetGradeRequest(UUID courseId, String letterGrade) {
        this.courseId = courseId;
        this.letterGrade = letterGrade;
    }
}
