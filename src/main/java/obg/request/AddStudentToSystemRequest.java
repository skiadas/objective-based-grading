package obg.request;

import obg.core.Request;

import java.util.UUID;

public class AddStudentToSystemRequest implements Request {

    public final String userName;

    public AddStudentToSystemRequest(String userName) {
        this.userName = userName;
    }

//    public String getUserName() {
//        return userName;
//    }
}
