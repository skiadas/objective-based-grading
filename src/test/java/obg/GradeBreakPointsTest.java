package obg;

import junit.framework.TestCase;
import obg.core.entity.GradeBreakPoints;
import obg.core.entity.ObjectiveGroup;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;

import static org.junit.Assert.*;

public class GradeBreakPointsTest {


    @Test
    public void addEntryTest() {
        GradeBreakPoints gbp = new GradeBreakPoints();
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(ObjectiveGroup.class);
        GradeBreakPoints.BreakpointEntry bpe = new GradeBreakPoints.BreakpointEntry("A", enumMap);
        gbp.addEntry(bpe);
        assertTrue(gbp.getBreakpointEntries().contains(bpe));
    }

    @Test
    public void addTest() {
        GradeBreakPoints gbp = new GradeBreakPoints();
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(ObjectiveGroup.class);
        gbp.add("A", enumMap);
        ArrayList<GradeBreakPoints.BreakpointEntry> breakpointEntries = new ArrayList<>();
        breakpointEntries.add(gbp.getBreakpointEntries().get(0));
        assertEquals(gbp.getBreakpointEntries(), breakpointEntries);
    }

    @Test
    public void removeTest(){
        GradeBreakPoints gbp = new GradeBreakPoints();
        EnumMap<ObjectiveGroup, Integer> enumMap = new EnumMap<>(ObjectiveGroup.class);
        GradeBreakPoints.BreakpointEntry bpe2 = new GradeBreakPoints.BreakpointEntry("A", enumMap);
        GradeBreakPoints.BreakpointEntry bpe = new GradeBreakPoints.BreakpointEntry("A", enumMap);
        gbp.addEntry(bpe);
        gbp.addEntry(bpe2);
        gbp.remove(bpe, "A");
        assertTrue(gbp.getBreakpointEntries().contains(bpe2));
        assertFalse(gbp.getBreakpointEntries().contains(bpe));
    }
}