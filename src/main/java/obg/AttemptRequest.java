package obg;

import java.util.UUID;

public class AttemptRequest {
    private final String userName;
    private final UUID courseID;
    private final String objective;


    public AttemptRequest(String userName, UUID courseID, String objective) {
        this.userName = userName;
        this.courseID = courseID;
        this.objective = objective;
    }

    public String getUserName() {
        return userName;
    }

    public UUID getCourseID() {
        return courseID;

    }

    public String getObjective() {
        return objective;
    }
}
