package obg.core.entity;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GradeBreakPointTest {
    private GradeBreakPoints testPoints = new GradeBreakPoints();

    @Test
    public void canGetBreakPointEntry () {
       ArrayList<GradeBreakPoints.BreakpointEntry> testList;
       testList = testPoints.getBreakpointEntries();
       //assertEquals(testList[0].getLetterGrade(),"A");
    }
}
