package obg.core.entity;

import java.util.Objects;

public class Instructor {
    private final String instructorId;
    private String first;
    private String last;

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
}
