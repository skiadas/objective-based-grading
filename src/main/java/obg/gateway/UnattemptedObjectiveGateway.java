package obg.gateway;

import obg.core.entity.Enrollment;

import java.util.UUID;

public interface UnattemptedObjectiveGateway {

    Enrollment getEnrollment(UUID courseId, String studentId);
}


