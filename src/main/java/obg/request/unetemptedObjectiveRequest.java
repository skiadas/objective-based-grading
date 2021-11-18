package obg.request;

import java.util.UUID;

public class unetemptedObjectiveRequest {
    public String username;
    public UUID courserID;

    public unetemptedObjectiveRequest(String username, UUID courserID) {
        this.username = username;
        this.courserID = courserID;
    }


}
