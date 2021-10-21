package obg;

import java.util.UUID;

public class AttemptResponse {

    private final String userName;
    private final UUID courseID;
    private final String objective;
    private final String attemptStatus;

    public AttemptResponse(String userName, UUID courseID, String objective, String attemptStatus) {

        this.userName = userName;
        this.courseID = courseID;
        this.objective = objective;
        this.attemptStatus = attemptStatus;
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


    public String getAttemptStatus() {
        return attemptStatus;
    }
}
