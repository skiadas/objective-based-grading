package obg;

import java.util.UUID;

public class InvalidRequestCourseGateway implements AttemptRequestGateway{
    @Override
    public Course getCourse(UUID courseId) {
        return null;
    }

    @Override
    public Student getStudent(Student student) {
        return null;
    }


}
