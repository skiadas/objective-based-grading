package obg.request;

import java.util.UUID;

public class UnattemptedObjectiveRequest {
    public String userName;
    public UUID courseID;

    public UnattemptedObjectiveRequest(String username, UUID courseID) {
        this.userName = username;
        this.courseID = courseID;
    }


}
