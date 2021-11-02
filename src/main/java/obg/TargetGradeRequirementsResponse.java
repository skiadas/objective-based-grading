package obg;

import java.util.EnumMap;
import java.util.Objects;

public class TargetGradeRequirementsResponse implements Response{

    public final String grade;
    public static EnumMap objectiveRequirements = new EnumMap<>(ObjectiveGroup.class);

    public TargetGradeRequirementsResponse(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetGradeRequirementsResponse that = (TargetGradeRequirementsResponse) o;
        return Objects.equals(grade, that.grade) && Objects.equals(objectiveRequirements, that.objectiveRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, objectiveRequirements);
    }
}
