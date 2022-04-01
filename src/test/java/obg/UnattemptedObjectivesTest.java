package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Student;
import obg.gateway.UnattemptedObjectiveGateway;
import obg.interactor.UnattemptedObjectiveInteractor;
import obg.request.UnattemptedObjectiveRequest;
import org.junit.Before;
import org.junit.Ignore;
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
    private Course course1;
    private Enrollment enroll;

    @Before
    public void setUp() {
        gateway = mock(UnattemptedObjectiveGateway.class);
        request = new UnattemptedObjectiveRequest("Dave", UUID.randomUUID());
        interactor = new UnattemptedObjectiveInteractor(gateway);
        presenter = mock(Presenter.class);
        student = new Student(UUID.randomUUID(), request.studentId);
        course = new Course(null, null, null);
        enroll = new Enrollment(new Course(request.courseId, "course1"), new Student(UUID.randomUUID(), request.studentId));
    }

    @Test
    public void InvalidEnrollmentTest(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(null);
        interactor.handle(request, presenter);
        System.out.println("Value of student id: " + request.studentId);
        verify(presenter).reportError(ErrorResponse.INVALID_ENROLLMENT);
    }

    @Test
    public void InvalidStudentTest(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void InvalidCourseTest(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getCourse(request.courseId)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void StudentNotInCourse(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getStudentIsEnrolled(request.studentId, request.courseId)).thenReturn(false);
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
    }

    @Test
    public void ReturnStudentUnattemptedObjectives(){
        when(gateway.getEnrollment(request.courseId, request.studentId)).thenReturn(enroll);
        List<String> objectiveList = List.of("B1", "B2", "C3", "C4");
        when(gateway.getStudent(request.studentId)).thenReturn(student);
        when(gateway.getCourse(request.courseId)).thenReturn(course);
        when(gateway.getStudentIsEnrolled(request.studentId, request.courseId)).thenReturn(true);
        when(gateway.getUnattemptedObjectives(request.studentId, request.courseId)).thenReturn(objectiveList);
        interactor.handle(request, presenter);
        verify(presenter).presentUnattemptedObjectives(objectiveList);
    }
}
