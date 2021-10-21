package obg;

import java.util.UUID;

public class AttemptRequestRequest {
    public final String userName;
    public final UUID courseID;
    public final String objective;


    public AttemptRequestRequest(String userName, UUID courseID, String objective) {
        this.userName = userName;
        this.courseID = courseID;
        this.objective = objective;
    }

}
