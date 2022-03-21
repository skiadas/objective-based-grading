package obg.core.entity;
import org.junit.Test;


import java.util.EnumMap;
import java.util.Map;

import static obg.core.entity.ObjectiveGroup.*;
import static org.junit.Assert.*;

public class GradeBreakPointTest {
    private GradeBreakPoints gbp = new GradeBreakPoints();

    @Test
    public void addTest() {
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
        gbp.add("A", enumMap);
        assertEquals(gbp.getLetterGrade(enumMap), "A");
        assertEquals(gbp.getScore("A"), enumMap);
    }

    @Test
    public void addEntryTest() {
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
        gbp.addEntry(new GradeBreakPoints.BreakpointEntry("A", enumMap));
        assertEquals(gbp.getLetterGrade(enumMap), "A");
        assertEquals(gbp.getScore("A"), enumMap);
    }

    @Test
    public void removeTest() {
        boolean thrown = false;
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3));
        EnumMap<ObjectiveGroup, Integer> enumMap2 = new EnumMap<>(Map.of(BASIC, 4, CORE, 3, EXTRA, 3));
        gbp.add("A", enumMap);
        gbp.add("B", enumMap2);
        gbp.remove("B");

        try {
            gbp.getScore("B");
        } catch (RuntimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
        assertEquals(gbp.getScore("A"), enumMap);
    }


    @Test
    public void hasSameScoreAsTrue() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertTrue(be.hasSameScoreAs(be2));
    }

    @Test
    public void hasSameScoreAsFalse() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 3, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertFalse(be.hasSameScoreAs(be2));
    }

    @Test
    public void isNoLessThanTrue() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertTrue(be.isNoLessThan(be2));
    }

    @Test
    public void isNoLessFalse() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 5, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertFalse(be.isNoLessThan(be2));
    }

    @Test
    public void isNoMoreThanTrue() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertTrue(be.isNoMoreThan(be2));
    }

    @Test
    public void isNoMoreThanFalse() {
        GradeBreakPoints.BreakpointEntry be = new GradeBreakPoints.BreakpointEntry("A", new EnumMap<>(Map.of(BASIC, 4, CORE, 4, EXTRA, 3)));
        GradeBreakPoints.BreakpointEntry be2 = new GradeBreakPoints.BreakpointEntry("B", new EnumMap<>(Map.of(BASIC, 4, CORE, 3, EXTRA, 3)));
        gbp.addEntry(be);
        gbp.addEntry(be2);
        assertFalse(be.isNoMoreThan(be2));
    }
}
