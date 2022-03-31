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

    public Student getEnrolledStudent() {
        return student;
    }

    public Course getEnrolledCourse() {
        return course;
    }

    public boolean checkWithdrawn() {
        return withdrawn;
    }

    public String getEnrollmentDate() {
        return date;
    }

    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", course=" + course +
                ", student=" + student +
                ", date='" + date + '\'' +
                ", withdrawn=" + withdrawn +
                ", attemptMap=" + attemptMap +
                '}';
    }

    public AttemptMap getAttemptMap() {
        return attemptMap;
    }

    public void addAttempt(Attempt attempt) {
        attemptMap.add(attempt.objective, attempt);
    }

    public void getObjectName() {
    }

    public void removeStudent() {
        course.removeStudent(student);
        withdrawn = true;
        course=null;
        student=null;
    }

    public void setWithdrawn(boolean b) {
        withdrawn = b;
    }

    public long getLongId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(course, that.course) && Objects.equals(student, that.student) && Objects.equals(date, that.date) && withdrawn.equals(that.withdrawn) && Objects.equals(attemptMap, that.attemptMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, student, date, withdrawn, attemptMap);
    }

    public ArrayList<String> getUnattemptedObjectives() {
        ArrayList<String> objs = new ArrayList<>();
        for (String obj : course.objectives){
            if(!getAttemptMap().objectiveMap.containsKey(obj)){
                objs.add(obj);
            }
        }
        return objs;
    }
}
