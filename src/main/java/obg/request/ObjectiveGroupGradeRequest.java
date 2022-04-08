package obg.request;

import obg.core.entity.ObjectiveGroup;

import java.util.UUID;

public class ObjectiveGroupGradeRequest {

     public final UUID studentID;
     public final UUID courseID;
     public String objective;

    public ObjectiveGroupGradeRequest(UUID courseID, UUID studentId, String objective) {
        this.courseID = courseID;
        this.studentID = studentId;
        this.objective = objective;
    }

}
