package obg;

import java.util.HashMap;
import java.util.UUID;

public class ViewTargetGradeCourse {

    private HashMap<String, Object> gradeBreaks = new HashMap<>();
    private final UUID courseId;
    private final String courseName;

    public ViewTargetGradeCourse(String name, UUID Id) {
        this.courseId = Id;
        this.courseName = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public HashMap<String, Object> getGradeBreaks() {
        return gradeBreaks;
    }
}
