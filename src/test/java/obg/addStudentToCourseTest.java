package obg;

import obg.request.addStudentToCourseRequest;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class addStudentToCourseTest {

    @Test
    public void canCreateAddStudentToCourseRequest() {
        String instructorId = "InstructorId";
        String courseId = "courseId";
        String studentId = "studentId";
        addStudentToCourseRequest request = new addStudentToCourseRequest(instructorId, courseId, studentId);
        assertEquals(instructorId, request.instructorId);
        assertEquals(courseId, request.courseId);
        assertEquals(studentId, request.studentId);
    }
}
