package obg;

import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class ViewTargetGradeRequestTests {

    @Test
    public void canCreateViewTargetGradeRequest() {
        ViewTargetGradeRequest request = new ViewTargetGradeRequest(UUID.randomUUID(), "A");
        assertEquals("A", request.grade);
    }
}
