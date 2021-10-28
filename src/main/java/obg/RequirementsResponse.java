package obg;

import java.util.EnumMap;

public class RequirementsResponse {

    public final String grade;
    public final EnumMap<ObjectiveGroup, Integer> objectiveRequirements;

    public RequirementsResponse(String grade) {
        this.grade = grade;
        objectiveRequirements = new EnumMap<>(ObjectiveGroup.class);
    }
}
