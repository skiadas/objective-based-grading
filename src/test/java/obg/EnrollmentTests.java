package obg;

import obg.core.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class EnrollmentTests {
    private Course course;
    private Student student;
    private Enrollment enroll;
    private String date;
    private Boolean withdrawn;
    private UUID courseId;
    private UUID studentId;


    @Before
    public void setUp() throws Exception {
        courseId = UUID.randomUUID();
        studentId = UUID.randomUUID();
        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        date = "08-13-2022";
        withdrawn = false;
        enroll = new Enrollment(course, student, date, withdrawn);
    }

    @Test
    public void creatingEmptyEnrollmentList() {
        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertTrue(enroll.attemptMap.isEmpty());
    }

    @Test
    public void checkingStudentInEnrollment() {
        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertEquals(enroll.getStudent(), student);
    }

    @Test
    public void checkingCourseIsInEnrollment() {
        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertEquals(enroll.getCourse(), course);
    }

    @Test
    public void checkingWithdrawnIsInEnrollment() {
        assertEquals(courseId, course.courseId);
        assertEquals(studentId, student.studentId);
        assertFalse(enroll.getWithdrawn());
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
        assertEquals(enroll.getDate(), date);
    }

    @Test
    public void getUnattemptedObjectivesTest(){
        Course course2 = new Course(courseId, "test course");
        Enrollment enroll2 = new Enrollment(course2, student);

        course2.objectives.add( "obj1");
        course2.objectives.add("obj2");

        Attempt attempt = new Attempt("obj2", 1, enroll2);
        enroll2.addAttempt(attempt);

        ArrayList<String> objs = new ArrayList<>();
        objs.add("obj1");

        ArrayList<String> unattemptedObjectives = enroll2.getUnattemptedObjectives();
        assertNotEquals(course2.objectives, objs);
        assertEquals(objs , unattemptedObjectives);
    }

    @Test
    public void deleteObjectFromEnrollment(){
        Course course2 = new Course(courseId, "test course");
        Enrollment enroll2 = new Enrollment(course, student);

        course2.objectives.add( "obj1");
        course2.objectives.add("obj2");

        Attempt attempt = new Attempt("obj1", 1, enroll2);
        Attempt attempt2 = new Attempt("obj2", 2, enroll2);
        enroll2.addAttempt(attempt);
        enroll2.addAttempt(attempt2);
        enroll2.deleteObjective("obj2");

        assertNull(enroll2.getAttemptMap().getAttemptList("obj2"));
    }

    @Test
    public void computeObjectiveGradeTest(){
        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        enroll = new Enrollment(course, student);
        String obj = "obj1";
        course.addObjective(ObjectiveGroup.BASIC, obj);
        Attempt attempt = new Attempt("obj1", 1, enroll);
        Attempt attempt2 = new Attempt("obj1", 2, enroll);
        enroll.addAttempt(attempt);
        enroll.addAttempt(attempt2);
        attempt.assignScore(2);
        attempt2.assignScore(3);
        int objGrade = 0;
        objGrade = enroll.computeObjectiveGrade(obj);
        assertEquals(3, objGrade);


    }

    @Test
    public void enrollmentHasRemaingAttemptsField() {
        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();
        Course course1 = new Course(courseId, "test course");
        Student student1 = new Student(studentId, "Test student");
        int remainingAttempts1 = 40;
        int remainingAttempts2 = 6;
        Enrollment enrollment1 = new Enrollment(course1, student1, remainingAttempts1);
        Enrollment enrollment2 = new Enrollment(course1, student1, remainingAttempts2);
        assertEquals(remainingAttempts1,enrollment1.getRemainingAttempts());
        assertEquals(remainingAttempts2,enrollment2.getRemainingAttempts());
    }

    @Test
    public void canRemoveSingleRemainingAttemptFromEnrollment() {
        int remainingAttempts1 = 40;
        int remainingAttempts2 = 6;
        Enrollment enrollment1 = new Enrollment(course, student, remainingAttempts1);
        Enrollment enrollment2 = new Enrollment(course, student, remainingAttempts2);
        enrollment1.removeSingleAttempt();
        enrollment2.removeSingleAttempt();
        assertEquals(39, enrollment1.getRemainingAttempts());
        assertEquals(5, enrollment2.getRemainingAttempts());
    }
}
