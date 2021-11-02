package obg;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvalidObjectiveGateway implements AttemptRequestGateway {
    private final Course course1;

    public InvalidObjectiveGateway(Course course1) {
        this.course1 = course1;
    }

    @Override
    public Course getCourse(UUID courseId) {
        return new Course(courseId, null, null, null);
    }

    public Student getStudent(Student student) {
        return student;
    }

}
