package obg.core.entity;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GradeBreakPointTest {
    private GradeBreakPoints gradeBP = new GradeBreakPoints();

    @Test
    public void canGetBreakPointEntry () {
       ArrayList<GradeBreakPoints.BreakpointEntry> testList;
       testList = gradeBP.getBreakpointEntries();
       assertEquals(testList.get(0).getLetterGrade(),"A");
    }
}
