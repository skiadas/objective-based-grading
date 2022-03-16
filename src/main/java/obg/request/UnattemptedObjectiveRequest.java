package obg.request;

import java.util.UUID;

public class UnattemptedObjectiveRequest {
    public String userName;
    public UUID courseId;

    public UnattemptedObjectiveRequest(String username, UUID courseId) {
        this.userName = username;
        this.courseId = courseId;
    }


}
