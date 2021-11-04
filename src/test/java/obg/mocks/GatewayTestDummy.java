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

    public Student getStudent(Student student) {
        return null;
    }

    @Override
    public boolean objectiveInCourse(String objective, Course course) {
        return false;
    }

    @Override
    public boolean getStudentIsEnrolled(Student student1, Course course) {
        return false;
    }

    public Instructor getInstructor(String instructorId) {
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

    public boolean isValidObjective(String objective) {
        return false;
    }

    public Student getStudent(String username) {
        return null;
    }

    public void addInstructor(Instructor instructor) {

    }
}
