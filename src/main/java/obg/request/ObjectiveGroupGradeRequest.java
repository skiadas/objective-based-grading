package obg.request;

import obg.core.entity.ObjectiveGroup;

import java.util.UUID;

public class ObjectiveGroupGradeRequest {

     public final String studentID;
     public final UUID courseID;
     public ObjectiveGroup objGroup;

    public ObjectiveGroupGradeRequest(UUID courseID, String studentId, ObjectiveGroup objGroup) {
        this.courseID = courseID;
        this.studentID = studentId;
        this.objGroup = objGroup;
    }
}
