package obg.core.entity;
import org.junit.Test;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import static obg.core.entity.ObjectiveGroup.*;
import static org.junit.Assert.*;

public class GradeBreakPointTest {
    private GradeBreakPoints gradeBP = new GradeBreakPoints();

    @Test
    public void addTest() {
        GradeBreakPoints gbpt = new GradeBreakPoints();
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
        gbpt.add("A",enumMap);
        assertEquals(gbpt.getLetterGrade(enumMap), "A");
        assertEquals(gbpt.getScore("A"),enumMap);
    }

    @Test
    public void addEntryTest() {
       GradeBreakPoints gbpt = new GradeBreakPoints();
       EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
       gbpt.addEntry(new GradeBreakPoints.BreakpointEntry("A",enumMap));
       assertEquals(gbpt.getLetterGrade(enumMap), "A");
       assertEquals(gbpt.getScore("A"),enumMap);
    }

    @Test
    public void removeTest(){
        boolean thrown = false;
        GradeBreakPoints gbpt = new GradeBreakPoints();
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
        EnumMap<ObjectiveGroup, Integer> enumMap2 = new EnumMap<>(Map.of(BASIC, 4, CORE, 3, EXTRA, 3));
        gbpt.add("A",enumMap);
        gbpt.add("B",enumMap2);
        gbpt.remove("B");

        try{
            gbpt.getScore("B");
        } catch (RuntimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
        assertEquals(gbpt.getScore("A"),enumMap);
    }
}
