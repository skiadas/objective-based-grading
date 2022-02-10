package obg;

import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.AttemptRequestGateway;
import obg.request.ObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.UUID;

public class AttemptMapTest {
    public UUID randId;
    private Course course;
    private Student student;
    private ObjectiveGradeInteractor interactor;
    private AttemptRequestGateway gateway;
    private ObjectiveGradeRequest request;
    private Presenter presenter;

    @Before
    public void setUp() {
        gateway = mock(AttemptRequestGateway.class);
        randId = UUID.randomUUID();
        course = new Course(randId, null, null, null);
        request = new ObjectiveGradeRequest("Name", randId);
        student = new Student(UUID.randomUUID(), request.userName);
        interactor = new ObjectiveGradeInteractor(gateway);
    }

    @Test
    public void ValidMapDisplay() {
        AttemptMap map = new AttemptMap();
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(student.userName)).thenReturn(student);
    }

    @Test
    public void AddFirstAttemptToMap(){
        String obj1 = "obj1";
        Attempt obj1Attempt = new Attempt(obj1, 1, student, course);
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
        Attempt obj1Attempt = new Attempt(obj1, 1, student, course);
        Attempt obj2Attempt = new Attempt(obj2, 1, student, course);
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

}