package obg.mocks;

import obg.Course;
import obg.Gateway;
import obg.Instructor;
import obg.Student;

import java.util.List;
import java.util.UUID;

public class GatewayTestDummy implements Gateway {
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

    public boolean isValidCourse(Course course1) {
        return false;
    }

    public boolean isValidStudent(Student givenStudent) {
        return false;
    }

    @Override
    public boolean isValidObjective(String objective) {
        return false;
    }
}
