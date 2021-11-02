package obg;

import java.util.*;

public class Course {

    public UUID courseID;
    public String courseName;
    public ArrayList<String> students;
    public ArrayList<String> objectives;
    public HashMap <String, Object> gradeBreaks = new HashMap<>();

    public Course(UUID courseID, String courseName, ArrayList<String> students, ArrayList<String> objectives) {
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

    public boolean isValidObjective(String objective){
        return objectives.contains(objective);
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

}

