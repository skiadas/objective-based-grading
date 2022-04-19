package obg;

import obg.core.entity.*;
import obg.gateway.AttemptRequestGateway;
import obg.request.ObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttemptMapTest {
    public UUID randId;
    private Course course;
    private Student student;
    private AttemptRequestGateway gateway;
    private ObjectiveGradeRequest request;
    private Enrollment enrollment;

    @Before
    public void setUp() {
        gateway = mock(AttemptRequestGateway.class);
        randId = UUID.randomUUID();
        course = new Course(randId, null, null);
        request = new ObjectiveGradeRequest("Name", randId);
        student = new Student(UUID.randomUUID(), request.userName);
        enrollment = new Enrollment(course, student);
    }

    @Test
    public void ValidMapDisplay() {
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(student.userName)).thenReturn(student);
    }

    @Test
    public void AddFirstAttemptToMap(){
        String obj1 = "obj1";
        Attempt obj1Attempt = getAttempt(obj1);
        AttemptList attemptList = new AttemptList();
        attemptList.add(obj1Attempt);
        AttemptMap map = new AttemptMap();
        map.add(obj1,obj1Attempt);
        assertEquals(attemptList, map.getAttemptList(obj1));
    }

    @Test
    public void AddDifferentAttemptsToMap(){
        String obj1 = "obj1";
        String obj2 = "obj2";
        Attempt obj1Attempt = getAttempt(obj1);
        Attempt obj2Attempt = getAttempt(obj2);
        AttemptList attemptListObj1 = new AttemptList();
        attemptListObj1.add(obj1Attempt);
        AttemptList attemptListObj2 = new AttemptList();
        attemptListObj2.add(obj2Attempt);
        AttemptMap map = new AttemptMap();
        map.add(obj1,obj1Attempt);
        map.add(obj2, obj2Attempt);
        assertEquals(attemptListObj1, map.getAttemptList(obj1));
        assertEquals(attemptListObj2, map.getAttemptList(obj2));
    }

    private Attempt getAttempt(String obj1) {
        return new Attempt(obj1, 1, enrollment);
    }

}