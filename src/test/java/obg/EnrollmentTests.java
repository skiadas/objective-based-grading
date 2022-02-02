package obg;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnrollmentTests {
    private Course course;
    private Student student;
    private Enrollment enroll;

    @Test
    public void creatingEmptyEnrollmentList() {

        UUID courseID = UUID.randomUUID();
        UUID studentID = UUID.randomUUID();

        course = new Course(courseID, "test course");
        student = new Student(studentID, "Test student");

        enroll = new Enrollment(course, student);

        assertEquals(courseID, course.courseID);
        assertEquals(studentID, student.studentId);
        assertTrue(enroll.gradedObjectiveMap.isEmpty());
    }

}
