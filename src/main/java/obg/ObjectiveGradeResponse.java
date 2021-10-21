package obg;

import java.util.UUID;

public class ObjectiveGradeResponse {

    String studentId;
    UUID courseId;
    String objectiveId;
    int grade;
    String gradeStatus;

    public ObjectiveGradeResponse(String studentId, UUID courseId, String objectiveId, int grade, String gradeStatus){
        this.studentId = studentId;
        this.courseId = courseId;
        this.objectiveId = objectiveId;
        this.grade = grade;
        this.gradeStatus = gradeStatus;
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

    public String getGradeStatus(){
        return gradeStatus;
    }

}
