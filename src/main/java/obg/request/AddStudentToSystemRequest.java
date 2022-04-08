package obg.request;

import java.util.UUID;

public class AddStudentToSystemRequest {

    private String userName;

    public AddStudentToSystemRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
