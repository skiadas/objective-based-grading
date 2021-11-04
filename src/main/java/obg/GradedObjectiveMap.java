package obg;

import java.util.HashMap;
import java.util.List;

public class GradedObjectiveMap {

    HashMap<String, ObjectiveAttemptList> map = new HashMap<>();


    public void add(String obj, List<Integer> aScore) {

        if (map.containsKey(obj)) {
            map.get(obj).addAll(aScore);
        }
        else {
            map.put(obj, new ObjectiveAttemptList());
        }

    }

    public String getObjective(String objective) {
        return objective;
    }

}
