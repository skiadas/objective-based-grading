package obg.request;

import obg.core.entity.Enrollment;

public class RemoveStudentRequest {

    public final Enrollment enrollment;

    public RemoveStudentRequest(Enrollment e) {
        enrollment = e;
    }
}
