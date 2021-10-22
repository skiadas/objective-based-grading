package obg;

import java.util.UUID;

public class ARInteractor {

    public static Response handle(AttemptRequestRequest request){
        String requestUserName = request.userName;
        Gateway studentGateway = new Gateway();
        ErrorResponse errResponse = new ErrorResponse("Invalid Student");
        AttemptRequestResponse attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
        if(studentGateway.isValidStudent(requestUserName)) return attemptResponse;
        else return errResponse;



    }
}
