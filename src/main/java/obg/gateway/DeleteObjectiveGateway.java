package obg.gateway;

import obg.core.entity.Enrollment;

public interface DeleteObjectiveGateway {
    Enrollment deleteObjective(String obj1, Enrollment enrollment);
}
