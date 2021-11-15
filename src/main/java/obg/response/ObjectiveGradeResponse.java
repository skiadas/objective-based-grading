package obg.response;

import java.util.UUID;

public class ObjectiveGradeResponse {

    String studentId;
    UUID courseId;
    String gradeStatus;

    public ObjectiveGradeResponse(String studentId, UUID courseId, String gradeStatus) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.gradeStatus = gradeStatus;
    }


    public String getStudentId() {
        return studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getGradeStatus() {
        return gradeStatus;
    }

}
