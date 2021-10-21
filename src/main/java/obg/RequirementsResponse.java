package obg;

import java.util.HashMap;

public class RequirementsResponse {
    public final String grade;
    public final HashMap<String, Integer> objectiveRequirements = new HashMap<>();

    public RequirementsResponse(String grade) {
        this.grade = grade;
    }
}
