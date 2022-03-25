package obg.gateway;

import obg.core.entity.Instructor;

public interface CreateNewInstructorGateway {
    Instructor getInstructor(String instructorId);
    <E>  void save(E entity);
}
