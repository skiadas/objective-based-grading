package obg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;

public class AttemptRequestTest {

    public UUID randID = randomUUID();
    public static final UUID testUUID = new UUID(0x6ba7b8109dad11d1L, 0x80b400c04fd430c8L);
    private AttemptRequestRequest request = new AttemptRequestRequest("DoeJ24", randID, "L1");

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
    public void CheckIsCourseValid() {
        ArrayList<String> students = new ArrayList();
        ArrayList<String> objectives = new ArrayList();
        Course newCourse = new Course(testUUID, "course2", students, objectives);
        CourseTestGateway testGateway = new CourseTestGateway(newCourse);
        Course course1 = new Course(randID, "courseName", students, objectives);
        Course course2 = new Course(testUUID, "course2", students, objectives);
        assertTrue(testGateway.isValidCourse(course2));
        assertFalse(testGateway.isValidCourse(course1));

    }


}
