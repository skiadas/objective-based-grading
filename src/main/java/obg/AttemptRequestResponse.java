package obg;

import java.util.Objects;
import java.util.UUID;

public class AttemptRequestResponse implements Response {

    public final String userName;
    public final UUID courseID;
    public final String objective;
    public final String attemptStatus;

    public AttemptRequestResponse(String userName, UUID courseID, String objective, String attemptStatus) {
        this.userName = userName;
        this.courseID = courseID;
        this.objective = objective;
        this.attemptStatus = attemptStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptRequestResponse that = (AttemptRequestResponse) o;
        return Objects.equals(userName, that.userName) && Objects.equals(courseID, that.courseID) && Objects.equals(objective, that.objective) && Objects.equals(attemptStatus, that.attemptStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, courseID, objective, attemptStatus);
    }
}
