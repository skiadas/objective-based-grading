package obg.request;

public class CreateCourseRequest {

    public String instructorId;
    public String courseName;

    public CreateCourseRequest(String instructorId, String courseName) {
        this.instructorId = instructorId;
        this.courseName = courseName;
    }
}
