package obg.request;

import obg.core.Request;

public class AssignAttemptScoreRequest implements Request {

    public final String attemptId;
    public final int score;
    public final String instructorId;

    public AssignAttemptScoreRequest(String id, int score, String instructorId) {
        this.attemptId = id;
        this.score = score;
        this.instructorId = instructorId;
    }
}
