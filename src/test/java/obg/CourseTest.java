package obg;

import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

public class CourseTest {

    private Course course = new Course(UUID.randomUUID(), "");

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

    @Test
    public void removeObjectiveTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        course.removeObjective(ObjectiveGroup.BASIC);
        assertFalse(course.objectiveByGroups.containsKey(ObjectiveGroup.BASIC));
    }

    @Test(expected = RuntimeException.class)
    public void getObjectivesForExceptionTest() {
        course.getObjectivesFor(ObjectiveGroup.BASIC);
    }

}
