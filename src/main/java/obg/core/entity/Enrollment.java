package obg.core.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    public Course course;
    @ManyToOne
    public Student student;
    @Basic
    public String date;
    @Basic
    public Boolean withdrawn;

    @Basic
    private int remainingAttempts;

    // TODO: Need to eventually fix
    @Transient
    public AttemptMap attemptMap = new AttemptMap();

    protected Enrollment() {}

    public Enrollment(Course course, Student student, String date, Boolean withdrawn) {
        this.course = course;
        this.student = student;
        this.date = date;
        this.withdrawn = withdrawn;
    }

    public Enrollment(Course course, Student student) {
        this.course = course;
        this.student = student;
        withdrawn = false;
        date = new Date().toString();
    }

    public Enrollment(Course course, Student student, int remainingAttempts) {
        this(course, student);
        this.remainingAttempts = remainingAttempts;
    }

    public long getLongId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public boolean getWithdrawn() {
        return withdrawn;
    }

    public String getDate() {
        return date;
    }

    public AttemptMap getAttemptMap() {
        return attemptMap;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public void addRemainingAttempts(){remainingAttempts++;}

    public void removeRemainingAttempts(){remainingAttempts--;}

    public void addAttempt(Attempt attempt) {
        attemptMap.add(attempt.objective, attempt);
    }

    public void setWithdrawn(boolean b) {
        withdrawn = b;
    }

    public void removeStudent() {
        course.removeStudent(student);
        withdrawn = true;
        course = null;
        student = null;
    }

    public void removeSingleAttempt() {
        remainingAttempts = remainingAttempts - 1;
    }

    public ArrayList<String> getUnattemptedObjectives() {
        ArrayList<String> objs = new ArrayList<>();
        for (String obj : course.objectives) {
            if (!getAttemptMap().objectiveMap.containsKey(obj)) {
                objs.add(obj);
            }
        }
        return objs;
    }

    public int computeObjectiveGrade(String objective) {
        AttemptList attemptList = attemptMap.getAttemptList(objective);
        int maxGrade = 0;
        for (Attempt a : attemptList.list) {
            if (a.getScore() > maxGrade) {
                maxGrade = a.getScore();
            }
        }
        return maxGrade;
    }

    public String toString() {
        return "Enrollment{"
                + "id=" + id
                + ", course=" + course
                + ", student=" + student
                + ", date='" + date + '\''
                + ", withdrawn=" + withdrawn
                + ", attemptMap=" + attemptMap
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id)
                && Objects.equals(course, that.course)
                && Objects.equals(student, that.student)
                && Objects.equals(date, that.date)
                && withdrawn.equals(that.withdrawn)
                && Objects.equals(attemptMap, that.attemptMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, student, date, withdrawn, attemptMap);
    }

    public void deleteObjective(String object) {
        attemptMap.deleteObjective(object);
    }
}
