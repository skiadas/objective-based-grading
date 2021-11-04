package obg;

import java.util.HashMap;
import java.util.List;

public class GradedObjectiveMap {

    HashMap<String, ObjectiveAttemptList> map = new HashMap<>();

    public void createObjectiveList(String obj, List<Integer> aScore) {
        add(obj, aScore);
    }

    public void add(String obj, List<Integer> aScore) {

        if (objectiveMap.containsKey(obj)) {
            objectiveMap.get(obj).addAll(aScore);
        }
        else {
            objectiveMap.put(obj, new ObjectiveAttemptList());
        }

    }

    public String getObjective(String objective) {
        return objective;
    }
}
