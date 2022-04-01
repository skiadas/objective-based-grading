package main;

import obg.core.entity.*;
import obg.gateway.Gateway;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryGateway implements Gateway {
    public static Map<UUID, Course> courses = new HashMap<>();
    public static Map<String, Instructor> instructors = new HashMap<>();
    public static List<Pair<Course, Instructor>> courseInstructorPairs = new ArrayList<>();
    public static List<Pair<Course, Student>> courseStudentPairs = new ArrayList<>();
    public static Map<String, Student> students = new HashMap<>();
    public static List<Attempt> attempts = new ArrayList<>();

    @Override
    public Instructor getInstructor(UUID instructorId) {
        return null;
    }

    public Course getCourse(UUID courseId) {
        return courses.get(courseId);
    }

    @Override
    public Student getStudent(UUID studentId) {
        return null;
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {

    }

    @Override
    public List<Attempt> getAttempts(Course course) {
        List<Attempt> attempts = new ArrayList<>();
        for (Attempt attempt : InMemoryGateway.attempts) {
            if (attempt.getCourse() == course) {
                attempts.add(attempt);
            }
        }
        return attempts;
    }

    public Instructor getInstructor(String instructorId) {
        return instructors.get(instructorId);
    }

    public void saveInstructor(Instructor instructor) {
        instructors.put(instructor.getInstructorId(), instructor);
    }

    @Override
    public void saveAttempt(Attempt attempt) {
        attempts.add(attempt);
    }

    @Override
    public void removeAttempt(Long longId) {
        attempts.remove(longId);
    }

    public void clearAttempts() {
        attempts.clear();
    }

    public <E> void save(E o) {
        // TODO
    }

    @Override
    public void removeStudent(Enrollment enrollment1) {
        students.remove(enrollment1.getEnrolledStudent());
    }

    public void addStudent(Student student) { students.put(student.userName, student); }

    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return courseInstructorPairs.stream()
                .filter(pair->pair.second.equals(instructor))
                .map(pair->pair.first)
                .collect(Collectors.toList());
    }

    @Override
    public boolean objectiveInCourse(String objective, UUID courseId) {
        return false;
    }

    @Override
    public boolean getStudentIsEnrolled(String userName, UUID courseId) {
        for(Pair<Course, Student> p : courseStudentPairs ){
            if(p.first.courseId == courseId && p.second.userName == userName) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int getAttemptNumber() {
        return 0;
    }

    @Override
    public Enrollment getEnrollment(UUID courseId, String studentId) {
        return null;
    }

    @Override
    public Enrollment getEnrolledStudent() {
        return null;
    }

    @Override
    public Course getEnrolledCourse() {
        return null;
    }

    @Override
    public Attempt getAttempt(UUID id) {
        return null;
    }

    public void assignCourseInstructor(Course c, Instructor i) {
        courseInstructorPairs.add(new Pair<>(c, i));
    }

    public void assignCoursesToStudent(Course c, Student s) {
        courseStudentPairs.add(new Pair<>(c, s));
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
        return courses.put(course.courseId, course);
    }

    @Override
    public Enrollment getEnrollment(Enrollment enrollment) {
        return null;
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
