package obg;

import java.util.UUID;

public interface Gateway {

    Course getCourse(UUID courseId);

    boolean isValidLetterGrade(String letterGrade);
}
