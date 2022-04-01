package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.AttemptList;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;

import java.util.UUID;

public interface ComputeObjectiveGradeGateway {
    Enrollment getEnrollment(UUID courseId, String studentId);
    Attempt attempt(UUID courseId, String studentId, String objective);
    AttemptList getAttempt(String objective, Integer numAttempts);
}
