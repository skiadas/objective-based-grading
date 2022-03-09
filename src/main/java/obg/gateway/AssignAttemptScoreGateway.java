package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Instructor;

import java.util.UUID;

public interface AssignAttemptScoreGateway {

    Attempt getAttempt(UUID id);
    Instructor getInstructor(String id);

}
