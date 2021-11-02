package obg;

import java.util.UUID;

public interface ViewTargetGradeGateway {
    Course getCourse(UUID courseId);

    //TODO: move isValidLetterGrade to Course class or a new letterGrade class
    // TODO: and replace isValidLetterGrade with a getLetterGrade
    boolean isValidLetterGrade(String letterGrade);
}
