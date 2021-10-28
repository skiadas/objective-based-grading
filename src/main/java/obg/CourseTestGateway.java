package obg;

import java.util.List;
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

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }

    public Instructor getInstructor(UUID instructorId) {
        return null;
    }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return null;
    }
}