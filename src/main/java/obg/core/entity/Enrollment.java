package obg.core.entity;

public class Enrollment {
    public Course course;
    public Student student;
    public AttemptMap attemptMap = new AttemptMap();

    public Enrollment(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
}
