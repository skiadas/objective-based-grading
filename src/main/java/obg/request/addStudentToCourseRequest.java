package obg.request;

public class addStudentToCourseRequest {

    public final String instructorId;
    public final String courseId;
    public final String studentId;

    public addStudentToCourseRequest(String instructorId, String courseId, String studentId) {
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
