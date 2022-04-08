package obg;

import obg.core.ErrorResponse;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.ObjectiveGroupGradeGateway;
import obg.interactor.ObjectiveGroupGradeInteractor;
import obg.presenter.ObjectiveGroupGradePresenter;
import obg.request.ObjectiveGroupGradeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ObjectiveGroupGradeTest {

    private ObjectiveGroupGradeRequest request;
    private ObjectiveGroupGradeInteractor interactor;
    private ObjectiveGroupGradeGateway gateway;
    private ObjectiveGroupGradePresenter presenter;
    private final UUID studentID = UUID.randomUUID();
    private final UUID courseID = UUID.randomUUID();
    private Student student;
    private Course course;
    private Enrollment enrollment;

    @Before
    public void setUp() {
        course = new Course(courseID, "CS327");
        student = new Student(studentID, "Jay");
        enrollment = new Enrollment(course, student);
        request = new ObjectiveGroupGradeRequest(courseID, studentID, "L1");
        gateway = mock(ObjectiveGroupGradeGateway.class);
        presenter = mock(ObjectiveGroupGradePresenter.class);
        interactor = new ObjectiveGroupGradeInteractor(gateway);
    }

    @Test
    public void checkInvalidStudentTest() {
        when(gateway.getStudent(studentID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
        verify(gateway).getStudent(studentID);
    }

    @Test
    public void checkInvalidCourseTest() {
        when(gateway.getCourse(courseID)).thenReturn(null);
        when(gateway.getStudent(studentID)).thenReturn(student);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
        verify(gateway).getCourse(courseID);
    }

    @Test
    public void checkInvalidObjectiveTest() {
        ObjectiveGroupGradeRequest request1 = new ObjectiveGroupGradeRequest(courseID, studentID, "J1");
        when(gateway.getCourse(courseID)).thenReturn(course);
        when(gateway.getStudent(studentID)).thenReturn(student);
        interactor.handle(request1, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_OBJECTIVE);
    }

    @Test
    public void checkStudentIsNotInCourse() {
        when(gateway.getCourse(courseID)).thenReturn(course);
        when(gateway.getStudent(studentID)).thenReturn(student);
        when(gateway.getEnrollment(courseID, studentID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
        verify(gateway).getEnrollment(courseID, studentID);
    }

}
