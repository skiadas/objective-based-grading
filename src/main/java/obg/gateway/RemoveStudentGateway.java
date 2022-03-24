package obg.gateway;

import obg.core.entity.Enrollment;

public interface RemoveStudentGateway {

    void removeStudent(Enrollment e);

    Enrollment getEnrollment(Enrollment enrollment);
}
