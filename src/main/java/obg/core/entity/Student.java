package obg.core.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Student {
    public UUID studentId;
    public String userName;

    public Student(UUID studentId, String userName) {
        this.studentId = studentId;
        this.userName = userName;
    }

    public Student(Student givenStudent) {
        this.studentId = givenStudent.studentId;
        this.userName = givenStudent.userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(userName, student.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
