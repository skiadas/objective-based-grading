package obg.core.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AttemptList {

    List<Attempt> list = new ArrayList<>();

    public void addAll(AttemptList attempts) {
        list.addAll(attempts.list);
    }
    public void add(Attempt attempt){this.list.add(attempt); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptList that = (AttemptList) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
