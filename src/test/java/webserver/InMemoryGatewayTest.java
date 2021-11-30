package webserver;

import main.InMemoryGateway;
import obg.core.entity.*;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.ViewTargetGradeGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static obg.core.entity.AttemptStatus.ASSIGNED;
import static obg.core.entity.AttemptStatus.PENDING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;

public class InMemoryGatewayTest {

    private InMemoryGateway g;
    private Course course;
    private Student student;
    private Attempt attempt;

    @Before
    public void setUp() {
        g = new InMemoryGateway();
        course = makeCourse("course");
        student = makeStudent("student1");
        attempt = makeAttempt(course, "obj1", 1, student, PENDING);
    }

    @Test
    public void InMemoryGatewayImplementsGateway() {
        ViewTargetGradeGateway gg = g;
    }

    @Test
    public void canFindCourse() {
        Course c = makeCourse("course1");
        InMemoryGateway.courses.put(c.courseID, c);
        assertEquals(c, g.getCourse(c.courseID));
        assertNull(g.getCourse(UUID.randomUUID()));
    }

    @Test
    public void canFindInstructor() {
        Instructor i = makeInstructor("instr");
        InMemoryGateway.instructors.put(i.getInstructorId(), i);
        assertEquals(i, g.getInstructor(i.getInstructorId()));
        assertNull(g.getInstructor("unknown"));
    }

    @Test
    public void canFindCourseAssignedToInstructor() {
        Course c1 = makeCourse("course1");
        Course c2 = makeCourse("course2");
        Instructor i = makeInstructor("instr");
        InMemoryGateway.courses.put(c1.courseID, c1);
        InMemoryGateway.courses.put(c2.courseID, c2);
        InMemoryGateway.instructors.put(i.getInstructorId(), i);
        g.assignCourseInstructor(c1, i);
        assertEquals(List.of(c1), g.getCoursesTaughtBy(i));
    }

    @Test
    public void canAddAttemptToGateway() {
        g.addAttempt(attempt);
        assertEquals(List.of(attempt), InMemoryGateway.attempts);
        g.clearAttempts();
        assertTrue(InMemoryGateway.attempts.isEmpty());
    }

    @Test
    public void canGetAttemptsFromCourse() {
        g.addAttempt(attempt);
        Student student2 = makeStudent("student2");
        Attempt attempt2 = makeAttempt(course, "obj2", 2, student2, ASSIGNED);
        g.addAttempt(attempt2);
        List<Attempt> expected = List.of(attempt, attempt2);
        assertEquals(expected, g.getAttempts(course));
        g.clearAttempts();
        assertTrue(InMemoryGateway.attempts.isEmpty());
    }

    private Student makeStudent(String name) {
        return new Student(UUID.randomUUID(), name);
    }

    private Attempt makeAttempt(Course course, String objective, int attemptNum, Student student, AttemptStatus status) {
        return new Attempt(objective, attemptNum, student, course, status);
    }

    @Test
    public void canFindStudent() {
        Student s = makeStudent("s");
        InMemoryGateway.students.put(s.userName, s);
        assertEquals(s, g.getStudent(s.userName));
        assertNull(g.getStudent(""));
    }

    @Test
    public void canVerifyStudentEnrollment() {
        Course c1 = makeCourse("course1");
        Course c2 = makeCourse("course2");
        InMemoryGateway.courses.put(c1.courseID, c1);
        InMemoryGateway.courses.put(c2.courseID, c2);
        Student s = makeStudent("s");
        InMemoryGateway.students.put(s.userName, s);
        g.assignCoursesToStudent(c1, s);
        assertTrue(g.getStudentIsEnrolled(s.userName, c1.courseID));
    }

    private Course makeCourse(String name) {
        return new Course(UUID.randomUUID(), name);
    }

    private Instructor makeInstructor(String username) {
        return new Instructor(username);
    }

}
