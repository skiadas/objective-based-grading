package obg.core.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static obg.core.entity.ObjectiveGroup.*;
import static obg.core.entity.ObjectiveGroup.BASIC;

public class GradeBreakPoints {
    private final HashMap<String, EnumMap<ObjectiveGroup, Integer>> gradeBreaks = new HashMap<>();
    private ArrayList<BreakpointEntry> breakpointEntries = new ArrayList<>();

    public static GradeBreakPoints prePopulated() {
        GradeBreakPoints gradeBreakPoints = new GradeBreakPoints();
        gradeBreakPoints.populateGradeBreaks();
        return gradeBreakPoints;
    }

    public GradeBreakPoints() {

    }

    void populateGradeBreaks() {
        addEntry(new BreakpointEntry("A", targetScores(4, 4, 3)));
        addEntry(new BreakpointEntry("A-", targetScores(4, 3, 2)));
        addEntry(new BreakpointEntry("B+", targetScores(3, 3, 2)));
        addEntry(new BreakpointEntry("B", targetScores(3, 3, 1)));
        addEntry(new BreakpointEntry("B-", targetScores(3, 2, 1)));
        addEntry(new BreakpointEntry("C+", targetScores(3, 2, 0)));
        addEntry(new BreakpointEntry("C", targetScores(2, 2, 0)));
        addEntry(new BreakpointEntry("C-", targetScores(2, 1, 0)));
        addEntry(new BreakpointEntry("D+", targetScores(1, 1, 0)));
        addEntry(new BreakpointEntry("D", targetScores(1, 0, 0)));
        addEntry(new BreakpointEntry("F", targetScores(0, 0, 0)));
    }

    private EnumMap<ObjectiveGroup, Integer> targetScores(int b, int c, int e) {
        return new EnumMap<>(Map.of(BASIC, b, CORE, c, EXTRA, e));
    }

    public EnumMap<ObjectiveGroup, Integer> getScore(String letterGrade) {
        for (BreakpointEntry be :
                breakpointEntries) {
            if (be.letterGrade.equals(letterGrade)) {
                return be.targetScores;
            }
        }
        throw new RuntimeException("No matching breakpoint found");
    }

    public String getLetterGrade(EnumMap<ObjectiveGroup,Integer> targetScore){
        for (BreakpointEntry be :
                breakpointEntries) {
            if(be.targetScores == targetScore){
                return be.letterGrade;
            }
        }
        throw new RuntimeException("No Matching breakpoint found");
    }

    public boolean isValidScore(int score) {
        return score >= 0;
    }

    public void addEntry(BreakpointEntry entry) {
        breakpointEntries.add(entry);
    }

    public void add(String letterGrade, EnumMap<ObjectiveGroup, Integer> targetScores){
        BreakpointEntry entry = new BreakpointEntry(letterGrade, targetScores );
        addEntry(entry);
    }

    public void remove(String letterGrade){
        breakpointEntries.removeIf(i -> i.letterGrade.equals(letterGrade));
    }

    public static class BreakpointEntry {
        private String letterGrade;
        private EnumMap<ObjectiveGroup, Integer> targetScores;

        public BreakpointEntry(String letterGrade, EnumMap<ObjectiveGroup, Integer> targetScores) {
            this.letterGrade = letterGrade;
            this.targetScores = targetScores;
        }

        public boolean hasSameScoreAs(BreakpointEntry be) {
           return this.targetScores.get(BASIC) == be.targetScores.get(BASIC) &&
                   this.targetScores.get(CORE) == be.targetScores.get(CORE) &&
                   this.targetScores.get(EXTRA) == be.targetScores.get(EXTRA);

        }

        public boolean isNoLessThan(BreakpointEntry be) {
            return this.targetScores.get(BASIC) >= be.targetScores.get(BASIC) &&
                    this.targetScores.get(CORE) >= be.targetScores.get(CORE) &&
                    this.targetScores.get(EXTRA) >= be.targetScores.get(EXTRA);
        }

        public boolean isNoMoreThan(BreakpointEntry be) {
            return this.targetScores.get(BASIC) <= be.targetScores.get(BASIC) &&
                    this.targetScores.get(CORE) <= be.targetScores.get(CORE) &&
                    this.targetScores.get(EXTRA) <= be.targetScores.get(EXTRA);
        }
    }
}

