package obg.core.entity;

import java.util.Objects;

public class Attempt {
    String objective;
    int attemptNumber;
    Student student;
    public Course course;
    public AttemptStatus status;
    public int score = 0;

    public Attempt(String objective, int attemptNumber, Student student, Course course, AttemptStatus status) {
        this.objective = objective;
        this.attemptNumber = attemptNumber;
        this.student = student;
        this.course = course;
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

    public enum AttemptStatus {
        PENDING,
        ASSIGNED,
        COMPLETED
    }
}
