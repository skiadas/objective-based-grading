package obg;

import obg.core.entity.Course;
import obg.request.ViewPendingAttemptsRequest;
import obg.response.ViewPendingAttemptsResponse;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ViewPendingRequestTests {

    @Test
    public void canCreateRequest() {
        UUID courseId = UUID.randomUUID();
        UUID instructorId = UUID.randomUUID();
        ViewPendingAttemptsRequest request = new ViewPendingAttemptsRequest(courseId, instructorId);
        assertEquals(courseId, request.courseId);
        assertEquals(instructorId, request.instructorId);
    }

    @Test
    public void canCreateResponse() {
        UUID courseId = UUID.randomUUID();
        Course course = new Course(courseId, "course");
        ViewPendingAttemptsResponse response = new ViewPendingAttemptsResponse(course);
        assertEquals(course, response.course);
        assertTrue(response.objectiveStatuses.isEmpty());
        assertTrue(response.studentObjectives.isEmpty());
    }
}
