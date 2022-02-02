package obg.request;

import obg.core.Request;
import obg.core.entity.Enrollment;

import java.util.UUID;

public class AttemptRequestRequest implements Request {
    public final String studentID;
    public final UUID courseID;
    public final String objective;


    public AttemptRequestRequest(String userName, UUID courseID, String objective) {
        this.studentID = userName;
        this.courseID = courseID;
        this.objective = objective;
    }

}
