package obg.request;

import obg.core.entity.Enrollment;

public class DeleteObjectiveRequest{
    public String obj1;
    public Enrollment enrollment;

    public DeleteObjectiveRequest(String obj1, Enrollment enrollment) {
        this.obj1 = obj1;
        this.enrollment = enrollment;
    }
}
