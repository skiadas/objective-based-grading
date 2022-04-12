package obg.core.entity;

import java.util.HashMap;
import java.util.Objects;

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

    public boolean isEmpty(){
        return objectiveMap.isEmpty();
    }

    public AttemptList getAttemptList(String obj) {
        return objectiveMap.get(obj);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptMap that = (AttemptMap) o;
        return Objects.equals(objectiveMap, that.objectiveMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectiveMap);
    }

    public void deleteObjective(String object) {
        objectiveMap.remove(object);
    }
}
