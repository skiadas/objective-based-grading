package obg;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class EnrollmentTests {
    private Course course;
    private Student student;
    private Enrollment enroll;

    @Test
    public void creatingEmptyEnrollmentList() {

        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        String date = "08-13-2022";
        Boolean withdrawn = false;

        enroll = new Enrollment(course, student, date, withdrawn);

        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertTrue(enroll.attemptMap.isEmpty());
    }

    @Test
    public void checkingStudentInEnrollment() {

        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        String date = "08-13-2022";
        Boolean withdrawn = false;

        enroll = new Enrollment(course, student, date, withdrawn);

        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertEquals(enroll.getEnrolledStudent(), student);
    }

    @Test
    public void checkingCourseIsInEnrollment() {

        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        String date = "08-13-2022";
        Boolean withdrawn = false;

        enroll = new Enrollment(course, student, date, withdrawn);

        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertEquals(enroll.getEnrolledCourse(), course);
    }

    @Test
    public void checkingWithdrawnIsInEnrollment() {

        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        String date = "08-13-2022";
        Boolean withdrawn = false;

        enroll = new Enrollment(course, student, date, withdrawn);

        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertFalse(enroll.checkWithdrawn());
    }

    @Test
    public void checkingCorrectDateInEnrollment() {

        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        String date = "08-13-2022";
        Boolean withdrawn = false;

        enroll = new Enrollment(course, student, date, withdrawn);

        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertEquals(enroll.getEnrollmentDate(), date);
    }
}
