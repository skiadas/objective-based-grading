package obg.core.entity;

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
}
