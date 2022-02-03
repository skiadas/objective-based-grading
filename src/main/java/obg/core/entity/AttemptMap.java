package obg.core.entity;

import java.util.HashMap;
import java.util.List;

public class AttemptMap {

    HashMap<String, AttemptList> objectiveMap = new HashMap<>();

    public void createObjectiveList(String obj, List<Attempt> aScore) {
        add(obj, aScore);
    }

    public void add(String obj, List<Attempt> aScore) {

        if (objectiveMap.containsKey(obj)) {
            objectiveMap.get(obj).addAll(aScore);
        }
        else {
            objectiveMap.put(obj, new AttemptList());
        }

    }

    public String getObjective(String objective) {
        return objective;
    }

    public boolean isEmpty(){
        return objectiveMap.isEmpty();
    }
}
