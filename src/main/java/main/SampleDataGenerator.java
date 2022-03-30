package main;

import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

import java.util.UUID;

public class SampleDataGenerator {
    private final InMemoryGateway gateway;

    public SampleDataGenerator(InMemoryGateway gateway) {
        this.gateway = gateway;
    }

    void populateWithData() {
        Instructor instructor = new Instructor("instructor", "Haris", "Skiadas");
        Course course = new Course(UUID.randomUUID(), "course1");
        Student student = new Student(UUID.randomUUID(), "student");
        gateway.saveInstructor(instructor);
        gateway.addCourse(course);
        gateway.assignCourseInstructor(course, instructor);
    }

}