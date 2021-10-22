package obg;

import org.junit.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

public class AttemptRequestTest {

    private UUID randID= randomUUID();
    private AttemptRequestRequest request = new AttemptRequestRequest("DoeJ24", randID, "L1");

    @Test
    public void getInstructortest () {


    }

    @Test
    public void canMakeAttemptRequest() {
        assertEquals("DoeJ24", request.userName);
        assertEquals(randID, request.courseID);
        assertEquals("L1", request.objective);
    }

    @Test
    public void canMakeAttemptRequestResponse() {
        AttemptRequestResponse response = new AttemptRequestResponse("DoeJ24", randID, "L1", "pending");
        assertEquals("DoeJ24", response.userName);
        assertEquals(randID, response.courseID);
        assertEquals("L1", response.objective);
        assertEquals("pending", response.attemptStatus);
    }

    @Test
    public void HandleRequestAndSeeStudentValidity(){
        Response response = ARInteractor.handle(request);
        ErrorResponse errResponse = new ErrorResponse("Invalid Student");
        assertEquals(errResponse, response);

    }
    @Test
    public void HandleRequestAndVerifyCourseInvalid(){
        Response response = ARInteractor.handle(request);
        ErrorResponse errResponse = new ErrorResponse("Invalid Course");
        assertEquals(errResponse,response);

    }


}
