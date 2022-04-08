package obg.gateway;

import obg.core.entity.Student;

public interface AddStudentToSystemGateway {
    void addStudent(String username);

    Student getStudentUsername(String username);
}
