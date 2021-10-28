package obg;

import java.util.EnumMap;
import java.util.Objects;

public class RequirementsResponse implements Response{

    public final String grade;
    public final EnumMap<ObjectiveGroup, Integer> objectiveRequirements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequirementsResponse that = (RequirementsResponse) o;
        return Objects.equals(grade, that.grade) && Objects.equals(objectiveRequirements, that.objectiveRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, objectiveRequirements);
    }

    public RequirementsResponse(String grade) {
        this.grade = grade;
        objectiveRequirements = new EnumMap<>(ObjectiveGroup.class);

    }
}
