package obg.request;

import obg.core.Request;

public class AddStudentToSystemRequest implements Request {

    public final String userName;

    public AddStudentToSystemRequest(String userName) {
        this.userName = userName;
    }

}
