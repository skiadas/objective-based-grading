package obg.core.entity;

import java.util.Objects;
import java.util.UUID;

import static obg.core.entity.Attempt.AttemptStatus.PENDING;

public class Attempt {

    private final UUID attemptId;
    String objective;
    int attemptNumber;
    private final Enrollment enrollment;
    public AttemptStatus status;
    private int score = 0;


    public Attempt(String objective, int attemptNumber, Enrollment enrollment) {
        this(UUID.randomUUID(), objective, attemptNumber, enrollment);
    }

    public Attempt(UUID attemptId, String objective, int attemptNumber, Enrollment enrollment) {
        this.objective = objective;
        this.attemptNumber = attemptNumber;
        this.attemptId = attemptId;
        this.enrollment = enrollment;
        status = PENDING;
    }

    public void setStatus(AttemptStatus status) {
        this.status = status;
    }

    public Student getStudent() {
        return enrollment.getEnrolledStudent();
    }

    public UUID getAttemptId() { return attemptId; }

    public Course getCourse() {
        return enrollment.getEnrolledCourse();
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(objective, attemptNumber, getStudent(), getCourse(), status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return attemptNumber == attempt.attemptNumber && Objects.equals(objective, attempt.objective) && Objects.equals(getStudent(), attempt.getStudent()) && Objects.equals(getCourse(), attempt.getCourse()) && status == attempt.status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public AttemptStatus getStatus() {
        return status;
    }

    public enum AttemptStatus {
        PENDING,
        ASSIGNED,
        COMPLETED,
        SCORED
    }
}
