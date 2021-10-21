package obg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequirementResponseTests {
    @Test
    public void canCreateRequirementsResponseObject() {
        RequirementsResponse response = new RequirementsResponse("A");
        assertEquals("A", response.grade);
    }

}
