package obg.core.entity;

import java.util.*;

public class Course {

    public UUID courseID;
    public Instructor instructor;
    public String courseName;
    public List<Student> students;
    public GradeBreakPoints gradeBreaks = new GradeBreakPoints();
    public EnumMap<ObjectiveGroup, String> objectiveByGroups = new EnumMap<>(ObjectiveGroup.class);


    public Course(UUID courseID, String courseName, List<Student> students) {
        this.students = students;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course(UUID courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
        students = new ArrayList<>();
    }


    public UUID getCourseId() {
        return courseID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return courseID == that.courseID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName, students);
    }

    public boolean isValidLetterGrade(String letterGrade) {
        List<String> letterGrades = new ArrayList<>(List.of("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        return letterGrades.contains(letterGrade);
    }

    public boolean isCourseInstructor(Instructor instructor) {
        return this.instructor == instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public boolean isValidObjective(String objective) {
        List<String> objectiveList = new ArrayList<>(List.of("L1", "L2", "S1", "S2", "C1", "C2"));
        return objectiveList.contains(objective);
    }

    public String getObjectivesFor(ObjectiveGroup group) {
        return objectiveByGroups.get(group);
    }

    public void addObjective(ObjectiveGroup group, String obj) {
        objectiveByGroups.put(group, obj);
    }

    public void removeObjective(ObjectiveGroup obj) {
        if (objectiveByGroups.containsKey(obj)) {
            objectiveByGroups.remove(obj);
        }
    }
}