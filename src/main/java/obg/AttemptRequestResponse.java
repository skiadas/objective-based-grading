package obg;

import java.util.UUID;

public class AttemptRequestResponse implements Response {

    public final String userName;
    public final UUID courseID;
    public final String objective;
    public final String attemptStatus;

    public AttemptRequestResponse(String userName, UUID courseID, String objective, String attemptStatus) {
        this.userName = userName;
        this.courseID = courseID;
        this.objective = objective;
        this.attemptStatus = attemptStatus;
    }
}
