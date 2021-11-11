package obg;

import java.util.HashMap;
import java.util.Map;

public class ViewPendingAttemptsResponse {


    Map<Student, HashMap<String, String>> studentObjectives = new HashMap<>();
    Map<String, String> objectiveStatuses = new HashMap<>();
    Course course;


    public ViewPendingAttemptsResponse(Course course) {
        this.course = course;
    }
}
