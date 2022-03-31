package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.Request;
import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import obg.core.entity.Student;
import obg.gateway.ObjectiveGroupGradeGateway;
import obg.interactor.ObjectiveGroupGradeInteractor;
import obg.presenter.ObjectiveGroupGradePresenter;
import obg.request.ObjectiveGradeRequest;
import obg.request.ObjectiveGroupGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ObjectiveGroupGradeTest {

    private Attempt attempt;
    private ObjectiveGroupGradeRequest request;
    private ObjectiveGroupGradeInteractor interactor;
    private ObjectiveGroupGradeGateway gateway;
    private ObjectiveGroupGradePresenter presenter;
    private String stringAttemptID;
    private UUID studentID = UUID.randomUUID();
    private UUID courseID = UUID.randomUUID();
    private Student student;
    private Course course;

    @Before
    public void setUp() {
        course = new Course(courseID, "CS327");
        student = new Student(studentID, "Jay");
        request = new ObjectiveGroupGradeRequest(courseID, studentID, ObjectiveGroup.BASIC);
        gateway = mock(ObjectiveGroupGradeGateway.class);
        presenter = mock(ObjectiveGroupGradePresenter.class);
        interactor = new ObjectiveGroupGradeInteractor(gateway);
    }

    @Test
    public void checkInvalidStudentTest() {
        when(gateway.getStudent(studentID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void checkInvalidCourseTest() {
        when(gateway.getCourse(courseID)).thenReturn(null);
        when(gateway.getStudent(studentID)).thenReturn(student);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void checkInvalidObjectiveTest() {
        course.removeObjective(ObjectiveGroup.BASIC);
        when(gateway.getCourse(courseID)).thenReturn(course);
        when(gateway.getStudent(studentID)).thenReturn(student);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_OBJECTIVE);
    }

}
