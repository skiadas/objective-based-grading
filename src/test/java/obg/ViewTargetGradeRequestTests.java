package obg;

import obg.request.ViewTargetGradeRequest;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class ViewTargetGradeRequestTests {

    @Test
    public void canCreateViewTargetGradeRequest() {
        UUID requestId = UUID.randomUUID();
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(requestId, "A");
        assertEquals(requestId, request.courseId);
        assertEquals("A", request.letterGrade);
    }
}
