package obg;

import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class CourseTest {

    private Course course = new Course(UUID.randomUUID(), "");

    @Test
    public void addObjectiveTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        assertTrue(course.objectivesByGroup.containsKey(ObjectiveGroup.BASIC));
    }

    @Test
    public void getObjectivesForTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        ArrayList<String> obj = course.getObjectivesFor(ObjectiveGroup.BASIC);
        ArrayList<String> obj2 = new ArrayList<>();
        obj2.add("S1");
        assertEquals(obj2, obj);
    }

    @Test
    public void removeObjectiveTest() {
        course.addObjective(ObjectiveGroup.BASIC, "S1");
        course.removeObjective(ObjectiveGroup.BASIC);
        assertFalse(course.objectivesByGroup.containsKey(ObjectiveGroup.BASIC));
    }

    @Test(expected = RuntimeException.class)
    public void getObjectivesForExceptionTest() {
        course.getObjectivesFor(ObjectiveGroup.BASIC);
    }

}
