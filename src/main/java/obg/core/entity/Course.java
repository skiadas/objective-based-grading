package obg.core.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Course {

    public UUID courseID;
    public Instructor instructor;
    public String courseName;
    public List<Student> students;
    public ArrayList<String> objectives;
    public GradeBreakPoints gradeBreaks = new GradeBreakPoints();


    public Course(UUID courseID, String courseName, List<Student> students, ArrayList<String> objectives) {
        this.students = students;
        this.objectives = objectives;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course(UUID courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
        students = new ArrayList<>();
        objectives = new ArrayList<>();
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
        return Objects.hash(courseID, courseName, students, objectives);
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
    
}