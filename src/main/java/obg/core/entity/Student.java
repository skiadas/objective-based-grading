package obg.core.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Basic
    @Column(columnDefinition = "uuid", unique = true)
    public UUID studentId;

    @Basic
    public String userName;

    @OneToMany(mappedBy = "student", cascade =CascadeType.ALL)
    public List<Enrollment> enrollments;

    protected Student() {}

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

    public UUID getStudentId() {
        return studentId;
    }
}
