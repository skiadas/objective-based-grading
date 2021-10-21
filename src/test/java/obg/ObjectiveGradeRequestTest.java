package obg;

import junit.framework.TestCase;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ObjectiveGradeRequestTest extends TestCase {

    public void testObjectiveGradeRequest() {
        UUID randID = randomUUID();
        ObjectiveGradeRequest objective = new ObjectiveGradeRequest("JackJ", randID, "S1", 4);
        assertEquals("JackJ", objective.getStudentId());
        assertEquals(randID, objective.getCourseId());
        assertEquals("S1", objective.getObjectiveId());
        assertEquals(4, objective.getGrade());
    }
}