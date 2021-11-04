package obg;

import java.util.EnumMap;
import java.util.Objects;

public class TargetGradeRequirementsResponse implements Response{

    public final String letterGrade;
    public EnumMap<ObjectiveGroup, Integer> objectiveRequirements = new EnumMap<>(ObjectiveGroup.class);

    public TargetGradeRequirementsResponse(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetGradeRequirementsResponse that = (TargetGradeRequirementsResponse) o;
        return Objects.equals(letterGrade, that.letterGrade) && Objects.equals(objectiveRequirements, that.objectiveRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letterGrade, objectiveRequirements);
    }
}
