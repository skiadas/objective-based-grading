package obg.request;

public class InstructorCanDeleteAttemptRequest {

    public final String instructorId;
    public final String attemptId;

    public InstructorCanDeleteAttemptRequest(String instructorId, String attemptId) {
        this.instructorId = instructorId;
        this.attemptId = attemptId;
    }
}
