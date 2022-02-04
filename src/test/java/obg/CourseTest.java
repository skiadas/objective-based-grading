package obg;

import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CourseTest {

    private Course course = new Course();

    @Test
    public void addObjectiveTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        assertTrue(course.objectiveByGroups.containsKey(ObjectiveGroup.BASIC));
    }

    @Test
    public void getObjectivesForTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        String obj = course.getObjectivesFor(ObjectiveGroup.BASIC);
        assertSame("S1", obj);
    }

}
