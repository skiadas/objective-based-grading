package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Instructor;

public interface AssignAttemptScoreGateway {


    Attempt getAttempt(String id);
    Instructor getInstructor(String id);
}
