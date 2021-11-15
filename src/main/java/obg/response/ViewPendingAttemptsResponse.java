package obg.response;

import obg.core.entity.Course;
import obg.core.entity.Student;

import java.util.HashMap;
import java.util.Map;

public class ViewPendingAttemptsResponse {


    public Map<Student, HashMap<String, String>> studentObjectives = new HashMap<>();
    public Map<String, String> objectiveStatuses = new HashMap<>();
    public Course course;


    public ViewPendingAttemptsResponse(Course course) {
        this.course = course;
    }
}
