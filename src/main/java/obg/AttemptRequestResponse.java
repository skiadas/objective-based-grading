package obg;

import java.util.Objects;

public class AttemptRequestResponse implements Response {

    private final Attempt attempt;


    public AttemptRequestResponse(Attempt attempt) {
        this.attempt = attempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptRequestResponse that = (AttemptRequestResponse) o;
        return Objects.equals(attempt, that.attempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt);
    }
}
