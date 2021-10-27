package obg;

import java.util.UUID;

public class AttemptRequestInteractor {

    public static Response handle(AttemptRequestRequest request) {
        String requestUserName = request.userName;
        CourseTestGateway studentGateway = new CourseTestGateway(null);
        AttemptRequestResponse attemptResponse = new AttemptRequestResponse("", UUID.randomUUID(), "", "");
        return getIsStudentResponse(requestUserName, studentGateway, attemptResponse);

    }

    private static Response getIsStudentResponse(String requestUserName, CourseTestGateway studentGateway, AttemptRequestResponse attemptResponse) {
        return attemptResponse;
    }
}
