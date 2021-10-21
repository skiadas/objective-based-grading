package obg;

import org.junit.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

public class AttemptRequestTest {
    @Test
    public void getInstructortest () {


    }

    @Test
    public void canMakeAttemptRequest() {
        UUID randID = randomUUID();
        AttemptRequest request = new AttemptRequest("DoeJ24", randID, "L1");
        assertEquals("DoeJ24", request.getUserName());
        assertEquals(randID, request.getCourseID());
        assertEquals("L1", request.getObjective());
    }

    @Test
    public void canMakeAttemptResponse() {
        UUID randID = randomUUID();
        AttemptResponse response = new AttemptResponse("DoeJ24", randID, "L1", "pending");
        assertEquals("DoeJ24", response.getUserName());
        assertEquals(randID, response.getCourseID());
        assertEquals("L1", response.getObjective());
        assertEquals("pending", response.getAttemptStatus());
    }



}
