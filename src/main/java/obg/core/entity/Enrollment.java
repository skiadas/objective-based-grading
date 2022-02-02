package obg.core.entity;

public class Enrollment {
    public Course course;
    public Student student;
    public GradedObjectiveMap gradedObjectiveMap = new GradedObjectiveMap();

    public Enrollment(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
}
