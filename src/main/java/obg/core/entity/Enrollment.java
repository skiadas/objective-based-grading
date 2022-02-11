package obg.core.entity;

public class Enrollment {
    public Course course;
    public Student student;
    public String date;
    public Boolean withdrawn;
    public AttemptMap attemptMap = new AttemptMap();

    public Enrollment(Course course, Student student, String date, Boolean withdrawn) {
        this.course = course;
        this.student = student;
        this.date = date;
        this.withdrawn = withdrawn;
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
}
