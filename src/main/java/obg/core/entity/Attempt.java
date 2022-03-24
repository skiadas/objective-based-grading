package obg.core.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static obg.core.entity.Attempt.AttemptStatus.PENDING;

@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(columnDefinition = "uuid", unique = true)
    private UUID attemptId;

    @Basic
    String objective;

    @Basic
    int attemptNumber;

    @ManyToOne
    private Enrollment enrollment;

    @Basic
    public AttemptStatus status;

    @Basic
    private int score = 0;


    protected Attempt() {}

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

    public Student getStudent() {
        return enrollment.getEnrolledStudent();
    }

    public UUID getAttemptId() { return attemptId; }

    public Long getLongId() {
        return id;
    }

    public Course getCourse() {
        return enrollment.getEnrolledCourse();
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public AttemptStatus getStatus() {
        return status;
    }

    public void setStatus(AttemptStatus status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void assignScore(int score) {
        setScore(score);
        setStatus(AttemptStatus.SCORED);
    }

    public boolean isInGradableStatus() {
        return status == PENDING;
    }

    public boolean isEnrollmentCourseInstructor(Instructor instructor) {
        return enrollment.getEnrolledCourse().isCourseInstructor(instructor);
    }

    public boolean isValidScore(int score) {
        return enrollment.getEnrolledCourse().gradeBreaks.isValidScore(score);
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

    public enum AttemptStatus {
        PENDING,
        ASSIGNED,
        COMPLETED,
        SCORED
    }
}
