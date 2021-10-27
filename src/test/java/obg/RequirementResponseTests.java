package obg;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RequirementResponseTests {
    @Test
    public void canCreateRequirementsResponseObject() {
        RequirementsResponse response = new RequirementsResponse("A");
        assertTrue(response.objectiveRequirements.isEmpty());
        assertEquals("A", response.grade);
    }


}
