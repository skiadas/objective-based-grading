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

    public ArrayList<BreakpointEntry> getBreakpointEntries() {
        return breakpointEntries;
    }

    public GradeBreakPoints() {
        this.populateGradeBreaks();
    }

    void populateGradeBreaks() {
        gradeBreaks.put("A", targetScores(4, 4, 3));
        gradeBreaks.put("A-", targetScores(4, 3, 2));
        gradeBreaks.put("B+", targetScores(3, 3, 2));
        gradeBreaks.put("B", targetScores(3, 3, 1));
        gradeBreaks.put("B-", targetScores(3, 2, 1));
        gradeBreaks.put("C+", targetScores(3, 2, 0));
        gradeBreaks.put("C", targetScores(2, 2, 0));
        gradeBreaks.put("C-", targetScores(2, 1, 0));
        gradeBreaks.put("D+", targetScores(1, 1, 0));
        gradeBreaks.put("D", targetScores(1, 0, 0));
        gradeBreaks.put("F", targetScores(0, 0, 0));
    }

    private EnumMap<ObjectiveGroup, Integer> targetScores(int b, int c, int e) {
        return new EnumMap<>(Map.of(BASIC, b, CORE, c, EXTRA, e));
    }

    public EnumMap<ObjectiveGroup, Integer> get(String letterGrade) {
        return gradeBreaks.get(letterGrade);
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

    public static class BreakpointEntry {
        private String letterGrade;
        private EnumMap<ObjectiveGroup, Integer> targetScores;

        public BreakpointEntry(String letterGrade, EnumMap<ObjectiveGroup, Integer> targetScores) {
            this.letterGrade = letterGrade;
            this.targetScores = targetScores;
        }

//        public void addEntry(BreakpointEntry entry) {
//            breakpointEntries.add(entry);
//        }

//        public void add(String letterGrade, int b, int c, int e) {
//            BreakpointEntry entry = new BreakpointEntry();
//            addEntry(entry);
//        }
    }
}

