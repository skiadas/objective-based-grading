package obg;

import java.util.HashMap;
import java.util.UUID;

public class ViewTargetGradeCourse {

    private HashMap<String, Object> gradeBreaks = new HashMap<>();
    private final UUID Id;
    private final String name;

    public ViewTargetGradeCourse(String name, UUID Id) {
        this.Id = Id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getGradeBreaks() {
        return gradeBreaks;
    }
}
