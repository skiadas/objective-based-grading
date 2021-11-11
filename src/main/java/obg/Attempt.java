package obg;

import java.util.Objects;
import java.util.UUID;

public class Attempt {
    String objective;
    int attemptNumber;
    Student student;
    Course course;
    AttemptStatus status;


    public Attempt(String objective, int attemptNumber, String userName, UUID courseID, AttemptStatus status) {
        this.objective = objective;
        this.attemptNumber = attemptNumber;
        this.student = new Student(null, userName, null);
        this.course = new Course(courseID, null, null, null);
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return attemptNumber == attempt.attemptNumber && Objects.equals(objective, attempt.objective) && Objects.equals(student, attempt.student) && Objects.equals(course, attempt.course) && status == attempt.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(objective, attemptNumber, student, course, status);
    }
}
