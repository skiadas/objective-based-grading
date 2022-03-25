package obg.request;

public class CreateNewInstructorRequest {
    public String instructorId;
    public String first;
    public String last;

    public CreateNewInstructorRequest(String instructorId, String first, String last) {
        this.instructorId = instructorId;
        this.first = first;
        this.last = last;
    }
}
