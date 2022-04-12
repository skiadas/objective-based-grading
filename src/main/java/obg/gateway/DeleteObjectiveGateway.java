package obg.gateway;

import obg.core.entity.Enrollment;

public interface DeleteObjectiveGateway {
    Object deleteObjective(String obj1, Enrollment enrollment);
}
