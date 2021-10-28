package obg;

import java.util.UUID;

public class ValidCourseGateway implements Gateway {
    Course course = new Course(UUID.randomUUID(), "CS321");

    @Override
    public Course getCourse(UUID courseId) {
        return course;
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }
}
