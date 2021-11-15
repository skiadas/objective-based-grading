package obg;

import junit.framework.TestCase;
import obg.request.ObjectiveGradeRequest;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ObjectiveGradeRequestTest extends TestCase {

    public void testObjectiveGradeRequest() {
        UUID randID = randomUUID();
        ObjectiveGradeRequest objective = new ObjectiveGradeRequest("JackJ", randID);
        assertEquals("JackJ", objective.getStudentId());
        assertEquals(randID, objective.getCourseId());
    }
}