package webserver;

import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.ViewTargetGradeGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InMemoryGatewayTest {

    private InMemoryGateway g;

    @Before
    public void setUp() {
        g = new InMemoryGateway();
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

    private Course makeCourse(String name) {
        return new Course(UUID.randomUUID(), name);
    }

    private Instructor makeInstructor(String username) {
        return new Instructor(username);
    }
}
