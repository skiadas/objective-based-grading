package obg;

import java.util.UUID;

public class InvalidCourseGateway implements Gateway {
    UUID providedCourseId;

    @Override
    public Course getCourse(UUID courseId) {
        providedCourseId = courseId;
        return null;
    }
}
