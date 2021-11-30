package main;

import obg.core.entity.Course;
import obg.gateway.Gateway;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryGateway implements Gateway {
    public static Map<UUID, Course> courses = new HashMap<>();
    public static Map<String, Instructor> instructors = new HashMap<>();
    public static List<Pair<Course, Instructor>> courseInstructorPairs = new ArrayList<>();
    public static List<Pair<Course, Student>> courseStudentPairs = new ArrayList<>();
    public static Map<String, Student> students = new HashMap<>();

    public Course getCourse(UUID courseId) {
        return courses.get(courseId);
    }

    public Instructor getInstructor(String instructorId) {
        return instructors.get(instructorId);
    }

    public void addInstructor(Instructor instructor) {
        instructors.put(instructor.getInstructorId(), instructor);
    }

    public void addStudent(Student student) { students.put(student.userName, student); }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return courseInstructorPairs.stream()
                .filter(pair->pair.second.equals(instructor))
                .map(pair->pair.first)
                .collect(Collectors.toList());
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

    @Override
    public HashMap getObjMap(String studentName, UUID courseID) {
        //TODO
        return null;
    }

    public void assignCourseInstructor(Course c, Instructor i) {
        courseInstructorPairs.add(new Pair<>(c, i));
    }


    public Student getStudent(String username) {
        return students.get(username);
    }

    public List<Course> getStudentCourses(String userName) {
        Student student = getStudent(userName);
        return courseStudentPairs.stream()
                .filter(p -> p.second.equals(student))
                .map(p->p.first)
                .collect(Collectors.toList());
    }

    Course addCourse(Course course) {
        return courses.put(course.courseID, course);
    }

    private static class Pair<T, S> {
        private final T first;
        private final S second;

        public Pair(T c, S i) {
            this.first = c;
            this.second = i;
        }

    }
}
