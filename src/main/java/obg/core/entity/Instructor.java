package obg.core.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String instructorId;
    @Basic
    private String first;
    @Basic
    private String last;

    @OneToMany(mappedBy = "instructor")
    public List<Course> courses;

    protected Instructor() {}

    public Instructor(String instructorId) {
        this.instructorId = instructorId;
    }

    public Instructor(String instructorId, String first, String last) {
        this.instructorId = instructorId;
        this.first = first;
        this.last = last;
    }

    public String getInstructorId() {
        return instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(instructorId, that.instructorId) && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, first, last);
    }

    public Long getId() {
        return id;
    }
}
