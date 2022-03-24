package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Instructor;

import java.util.UUID;

public interface InstructorCanDeleteAttemptGateway {

    Instructor getInstructor(UUID id);
    Attempt getAttempt(UUID id);
    void removeAttempt(Attempt attempt);
}
