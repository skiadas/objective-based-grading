package db;

import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class AttemptPersistenceTest {

    private final SqlBackedGatewayFactory gatewayFactory = SqlBackedGatewayFactory.getInstance();

    @Test
    public void canWriteAndReadAttempt() {
        String objective = "obj";
        int attemptNumber = 1;
        Course course = new Course(UUID.randomUUID(), UUID.randomUUID().toString());
        Student student = new Student(UUID.randomUUID(), UUID.randomUUID().toString());
        Enrollment enrollment = new Enrollment(course, student);
        Attempt attempt = new Attempt(objective, attemptNumber, enrollment);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student);
            gateway.save(course);
            gateway.save(enrollment);
            gateway.save(attempt);
        });
        gatewayFactory.doWithGateway(gateway -> {
            Attempt retrievedAttempt = gateway.getAttempt(attempt.getAttemptId());
            assertEquals(attempt.getAttemptId(), retrievedAttempt.getAttemptId());
            assertEquals(attempt.getScore(), retrievedAttempt.getScore());
            assertEquals(attempt.getStatus(), retrievedAttempt.getStatus());
            assertEquals(attempt.getEnrollment().getCourse(), retrievedAttempt.getEnrollment().getCourse());
            assertEquals(attempt.getEnrollment().getStudent(), retrievedAttempt.getEnrollment().getStudent());
        });
    }

    @Test(expected = NoResultException.class)
    public void canRemoveAttempt() {
        String objective = "obj";
        int attemptNumber = 1;
        Course course = new Course(UUID.randomUUID(), "course");
        Student student = new Student(UUID.randomUUID(), "student");
        Enrollment enrollment = new Enrollment(course, student);
        Attempt attempt = new Attempt(objective, attemptNumber, enrollment);
        gatewayFactory.doWithGateway(gateway -> {
            gateway.save(student);
            gateway.save(course);
            gateway.save(enrollment);
            gateway.save(attempt);
        });
        gatewayFactory.doWithGateway(gateway -> {
            gateway.removeAttempt(attempt.getLongId());
            gateway.getAttempt(attempt.getAttemptId());
        });
    }
}
