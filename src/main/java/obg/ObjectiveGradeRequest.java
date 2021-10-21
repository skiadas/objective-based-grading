package obg;

import java.util.UUID;

public class ObjectiveGradeRequest {

    String studentId;
    UUID courseId;
    String objectiveId;
    int grade;

    public ObjectiveGradeRequest(String studentId, UUID courseId, String objectiveId, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.objectiveId = objectiveId;
        this.grade = grade;
    }

    public String getStudentId(){
        return studentId;
    }

    public UUID getCourseId(){
        return courseId;
    }

    public String getObjectiveId(){
        return objectiveId;
    }

    public int getGrade(){
        return grade;
    }

}
