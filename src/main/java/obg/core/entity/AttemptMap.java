package obg.core.entity;

import java.util.HashMap;
import java.util.List;

public class AttemptMap {

    HashMap<String, AttemptList> objectiveMap = new HashMap<>();

    public void createObjectiveList(String obj, AttemptList attempts) {
        add(obj, attempts);
    }

    public void add(String obj, AttemptList attempts) {

        if (objectiveMap.containsKey(obj)) {
            objectiveMap.get(obj).addAll(attempts);
        }
        else {
            objectiveMap.put(obj, attempts);
        }
    }

    public void add(String obj, Attempt attempt){
        if (!objectiveMap.containsKey(obj)) {
            objectiveMap.put(obj, new AttemptList());
        }
        objectiveMap.get(obj).add(attempt);
    }



    public String getObjective(String objective) {
        return objective;
    }

    public boolean isEmpty(){
        return objectiveMap.isEmpty();
    }

    public AttemptList getAttemptList(String obj) {
        return objectiveMap.get(obj);
    }
}
