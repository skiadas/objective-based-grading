package obg.gateway;

import obg.core.entity.Instructor;

public interface CreateCourseGateway {
    Instructor getInstructor(String instructorId);
}
