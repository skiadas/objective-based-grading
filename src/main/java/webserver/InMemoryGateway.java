package webserver;

import obg.core.entity.Course;
import obg.gateway.Gateway;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryGateway implements Gateway {
    static Map<UUID, Course> courses = new HashMap<>();
    static Map<String, Instructor> instructors = new HashMap<>();
    static List<CourseInstructorPair> courseInstructorPairs = new ArrayList<>();
    static Map<String, Student> students = new HashMap<>();

    public Course getCourse(UUID courseId) {
        return courses.get(courseId);
    }

    public Instructor getInstructor(String instructorId) {
        return instructors.get(instructorId);
    }

    public void addInstructor(Instructor instructor) {
        instructors.put(instructor.getInstructorId(), instructor);
    }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return courseInstructorPairs.stream()
                .filter(pair->pair.instructor.equals(instructor))
                .map(pair->pair.course)
                .collect(Collectors.toList());
    }

    public Student getStudent(Student student) {
        return student;
    }

    @Override
    public boolean objectiveInCourse(String objective, UUID courseID) {
        return false;
    }

    @Override
    public boolean getStudentIsEnrolled(String userName, UUID courseID) {
        return false;
    }

    @Override
    public int getAttemptNumber() {
        return 0;
    }

    public void assignCourseInstructor(Course c, Instructor i) {
        courseInstructorPairs.add(new CourseInstructorPair(c, i));
    }

    public Student getStudent(String username) {
        return students.get(username);
    }

    Course addCourse(Course course) {
        return courses.put(course.courseID, course);
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
