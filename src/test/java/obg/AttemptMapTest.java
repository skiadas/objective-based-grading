package obg;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.AttemptMap;
import obg.core.entity.Student;
import obg.gateway.AttemptRequestGateway;
import obg.request.ObjectiveGradeRequest;
import org.junit.Before;
import org.junit.Test;
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
}