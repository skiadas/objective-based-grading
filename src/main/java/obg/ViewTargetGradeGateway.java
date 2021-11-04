package obg;

import java.util.UUID;

public interface ViewTargetGradeGateway {
    Course getCourse(UUID courseId);

    //TODO: move isValidLetterGrade to Course class or a new LetterGrade class and set it up
    boolean isValidLetterGrade(String letterGrade);
}
