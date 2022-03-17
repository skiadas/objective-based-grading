package obg.request;

import obg.core.Request;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;

import java.util.List;

public class ViewAssignedAttemtsRequest implements Request {
    public final Student student;
    public final Course course;
    public final List<String> objectives;

    public ViewAssignedAttemtsRequest(Student student, Course course, List<String> objectives) {
        this.course = course;
        this.student = student;
        this.objectives = objectives;
    }
}
