package obg;

import java.util.UUID;

public class CourseTestGateway implements Gateway {
    private Course providedCourse;

    public CourseTestGateway(Course providedCourse) {
        this.providedCourse = providedCourse;
    }


    public boolean isValidCourse(Course requestCourse) {
        return requestCourse.equals(providedCourse);
    }


    @Override
    public Course getCourse(UUID courseId) {
        return null;
    }
}