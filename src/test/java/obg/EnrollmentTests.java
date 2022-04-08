package obg;

import obg.core.entity.*;
import org.junit.Test;

import java.util.ArrayList;
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
        assertEquals(enroll.getStudent(), student);
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
        assertEquals(enroll.getCourse(), course);
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
        UUID courseId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();

        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        enroll = new Enrollment(course, student);

        course.objectives.add( "obj1");
        course.objectives.add("obj2");

        Attempt attempt = new Attempt("obj2", 1, enroll);
        enroll.addAttempt(attempt);

        ArrayList<String> objs = new ArrayList<>();
        objs.add("obj1");

        ArrayList<String> unattemptedObjectives = enroll.getUnattemptedObjectives();
        assertNotEquals(course.objectives, objs);
        assertEquals(objs , unattemptedObjectives);
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
        course = new Course(courseId, "test course");
        student = new Student(studentId, "Test student");
        Course course2 = new Course(UUID.randomUUID(), "course2");
        Student student2 = new Student(UUID.randomUUID(), "student2");
        int remainingAttempts1 = 40;
        int remainingAttempts2 = 6;
        Enrollment enrollment1 = new Enrollment(course, student, remainingAttempts1);
        Enrollment enrollment2 = new Enrollment(course, student, remainingAttempts2);
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
