package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Student;
import obg.gateway.UnattemptedObjectiveGateway;
import obg.interactor.UnattemptedObjectiveInteractor;
import obg.request.UnattemptedObjectiveRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class UnattemptedObjectivesTest {

    private UnattemptedObjectiveGateway gateway;
    private UnattemptedObjectiveRequest request;
    private UnattemptedObjectiveInteractor interactor;
    private Presenter presenter;
    private Student student;
    private Course course;

    @Before
    public void setUp() {
        gateway = mock(UnattemptedObjectiveGateway.class);
        request = new UnattemptedObjectiveRequest("Dave", UUID.randomUUID());
        interactor = new UnattemptedObjectiveInteractor(gateway);
        presenter = mock(Presenter.class);
        student = new Student(null, request.userName, null);
        course = new Course(null, null, null, null);
    }

    @Test
    public void InvalidStudentTest(){
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void InvalidCourseTest(){
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.getCourse(request.courseID)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void StudentNotInCourse(){
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.getStudentIsEnrolled(request.userName, request.courseID)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
    }

    @Test
    public void ReturnStudentUnattemptedObjectives(){
        List<String> objectiveList = List.of("B1", "B2", "C3", "C4");
        when(gateway.getStudent(request.userName)).thenReturn(student);
        when(gateway.getCourse(request.courseID)).thenReturn(course);
        when(gateway.getStudentIsEnrolled(request.userName, request.courseID)).thenReturn(true);
        when(gateway.getUnattemptedObjectives(request.userName, request.courseID)).thenReturn(objectiveList);
        interactor.handle(request, presenter);
        verify(presenter).presentUnattemptedObjectives(objectiveList);
    }
}
