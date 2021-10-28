package obg;

import java.util.UUID;

public class TargetGradeRequirementsGateway implements Gateway{
    @Override
    public Course getCourse(UUID courseId) {
        return null;
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }
}
