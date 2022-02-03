package obg.core.entity;

import java.util.ArrayList;
import java.util.List;

public class AttemptList {

    List<Attempt> list = new ArrayList<>();

    public void addAll(List<Attempt> objectiveAttemptList) {
        list.addAll(objectiveAttemptList);
    }
    public void add(Attempt attempt){this.list.add(attempt); }

}
