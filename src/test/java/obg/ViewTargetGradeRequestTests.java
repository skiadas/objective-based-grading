package obg;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class ViewTargetGradeRequestTests {

    @Test
    public void canCreateViewTargetGradeRequest() {
        ViewTargetGradeRequest request = new ViewTargetGradeRequest("youngm22", "A");
        assertEquals("youngm22", request.studentId);
        assertEquals("A", request.grade);
    }
}
