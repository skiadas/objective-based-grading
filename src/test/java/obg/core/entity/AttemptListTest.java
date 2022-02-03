package obg.core.entity;

import junit.framework.TestCase;
import obg.core.Presenter;
import obg.gateway.AttemptRequestGateway;
import obg.interactor.AttemptRequestInteractor;
import obg.request.AttemptRequestRequest;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;

public class AttemptListTest extends TestCase {

    private AttemptRequestGateway gateway;
    private AttemptRequestRequest request;
    private Course course;
    private Student student;
    private UUID randID;

    @Before
    public void setUp() {
        gateway = mock(AttemptRequestGateway.class);
        randID = randomUUID();
        request = new AttemptRequestRequest("DoeJ24", randID, "L1");
        course = new Course(randID, "courseName");
        student = new Student(randomUUID(), request.studentID);
    }

    private Attempt getAttempt2() {
        AttemptRequestGateway gateway2 = mock(AttemptRequestGateway.class);
        UUID randID2 = randomUUID();
        AttemptRequestRequest request2 = new AttemptRequestRequest("DoeJ24", randID, "L1");
        Course course2 = new Course(randID, "courseName");
        Student student2 = new Student(randomUUID(), request.studentID);
        return new Attempt(request2.objective, gateway2.getAttemptNumber(), student2, course2, Attempt.AttemptStatus.PENDING);
    }

    public void testAddAll() {
        AttemptList attemptList = new AttemptList();
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), student, course, Attempt.AttemptStatus.PENDING);
        Attempt attempt2 = getAttempt2();
        List<Attempt> list = new ArrayList<>();
        list.add(attempt);
        list.add(attempt2);
        attemptList.addAll(list);
        assertEquals(list, attemptList.list);
    }

    public void testAdd() {
        AttemptList attemptList = new AttemptList();
        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), student, course, Attempt.AttemptStatus.PENDING);
        attemptList.add(attempt);
        List<Attempt> list = new ArrayList<>();
        list.add(attempt);
        assertEquals(attemptList.list, list);
    }
}