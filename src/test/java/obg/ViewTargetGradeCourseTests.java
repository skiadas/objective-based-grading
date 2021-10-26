package obg;

import org.junit.Test;

import java.util.EnumMap;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ViewTargetGradeCourseTests {

    ViewTargetGradeCourse course = new ViewTargetGradeCourse("CS321", UUID.randomUUID());

    @Test
    public void canCreateCourseObject() {
        assertEquals("CS321", course.getName());
    }

    @Test
    public void courseCreatedWithGradeBreakMap() {
        assertTrue(course.getGradeBreaks().isEmpty());
    }

//    @Test
//    public void gradeBreaksMapIsPopulated() {
//        EnumMap<ObjectiveGroup, Integer> testGrades = new EnumMap<>(ObjectiveGroup.class);
//        testGrades.put(ObjectiveGroup.BASE, 4);
//        testGrades.put(ObjectiveGroup.CORE, 4);
//        testGrades.put(ObjectiveGroup.EXTRA, 3);
//        course.getGradeBreaks().populateGradeBreaks();
//        assertEquals(course.getGradeBreaks().get("A"), testGrades);
//    }
}
