package webserver;

import obg.Course;
import obg.Gateway;
import obg.Instructor;
import obg.Student;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryGateway implements Gateway {
    Map<UUID, Course> courses = new HashMap<>();
    Map<UUID, Instructor> instructors = new HashMap<>();
    List<CourseInstructorPair> courseInstructorPairs = new ArrayList<>();

    public Course getCourse(UUID courseId) {
        return courses.get(courseId);
    }

    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }

    public Instructor getInstructor(UUID instructorId) {
        return instructors.get(instructorId);
    }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return courseInstructorPairs.stream()
                .filter(pair->pair.instructor.equals(instructor))
                .map(pair->pair.course)
                .collect(Collectors.toList());
    }

    public boolean isValidCourse(Course course1) {
        return false;
    }

    public Student getStudent(Student student) {
        return student;
    }

    public boolean isValidObjective(String objective) {
        return false;
    }

    public void assignCourseInstructor(Course c, Instructor i) {
        courseInstructorPairs.add(new CourseInstructorPair(c, i));
    }

    private static class CourseInstructorPair {
        private final Course course;
        private final Instructor instructor;

        public CourseInstructorPair(Course c, Instructor i) {

            this.course = c;
            this.instructor = i;
        }
    }
}
