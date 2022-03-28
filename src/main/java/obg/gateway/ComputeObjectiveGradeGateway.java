package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.AttemptList;
import obg.core.entity.Course;

import java.util.UUID;

public interface ComputeObjectiveGradeGateway {
    Course getCourse(UUID courseId);
    Attempt attempt(UUID courseId, String studentId, String objective);
    AttemptList getAttempt(String objective, Integer numAttempts);
}
