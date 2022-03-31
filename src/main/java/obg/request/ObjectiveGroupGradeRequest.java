package obg.request;

import obg.core.entity.ObjectiveGroup;

import java.util.UUID;

public class ObjectiveGroupGradeRequest {

     public final UUID studentID;
     public final UUID courseID;
     public ObjectiveGroup group;

    public ObjectiveGroupGradeRequest(UUID courseID, UUID studentId, ObjectiveGroup group) {
        this.courseID = courseID;
        this.studentID = studentId;
        this.group = group;
    }

}
