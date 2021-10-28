package obg;

import obg.mocks.GatewayTestDummy;

import java.util.List;

public class InvalidStudentGateway extends GatewayTestDummy {
    List<Student> students;
    List<Course> courses;

    public InvalidStudentGateway(List<Student> students, List<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    public boolean isValidStudent(Student givenStudent) {
        return students.contains(givenStudent);
    }

    public boolean isValidCourse(Course course){
        return true;
    }

}
