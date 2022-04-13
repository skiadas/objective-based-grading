package obg.gateway;

import obg.core.entity.Student;

public interface AddStudentToSystemGateway {
    void addStudent(Student username);

    Student getStudentUsername(String username);
}
