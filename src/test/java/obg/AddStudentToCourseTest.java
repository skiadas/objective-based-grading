package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.AddStudentToCourseGateway;
import obg.interactor.AddStudentToCourseInteractor;
import obg.request.AddStudentToCourseRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static obg.core.ErrorResponse.EXISTING_ENROLLMENT;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class AddStudentToCourseTest {

    private String instructorId;
    private String courseId;
    private String studentId;
    private Presenter presenter;
    private AddStudentToCourseRequest request;
    private AddStudentToCourseGateway gateway;
    private AddStudentToCourseInteractor interactor;
    private Instructor instructor;
    private Course course;
    private Student student;

    @Before
    public void setUp() throws Exception {
        instructorId = UUID.randomUUID().toString();
        courseId = UUID.randomUUID().toString();
        studentId = UUID.randomUUID().toString();
        presenter = mock(Presenter.class);
        request = new AddStudentToCourseRequest(instructorId, courseId, studentId);
        gateway = mock(AddStudentToCourseGateway.class);
        interactor = new AddStudentToCourseInteractor(gateway, presenter);
        instructor = new Instructor(instructorId, "first", "last");
        course = new Course(UUID.randomUUID(), "course");
        student = new Student(UUID.randomUUID(), "student");
    }

    @Test
    public void canCreateAddStudentToCourseRequest() {
        String instructorId = "InstructorId";
        String courseId = "courseId";
        String studentId = "studentId";
        AddStudentToCourseRequest request = new AddStudentToCourseRequest(instructorId, courseId, studentId);
        assertEquals(instructorId, request.instructorId);
        assertEquals(courseId, request.courseId);
        assertEquals(studentId, request.studentId);
    }

    @Test
    public void interactorReportsInstructorDoesNotExist() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getInstructor(UUID.fromString(request.instructorId));
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);

    }

    @Test
    public void interactorReportsInvalidCourse() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getCourse(UUID.fromString(request.courseId));
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void interactorReportsNotCourseInstructor() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        // Not setting instructor as course instructor.
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.NOT_COURSE_INSTRUCTOR);
    }

    @Test
    public void interactorReportsInvalidStudent() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(null);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        course.setInstructor(instructor);
        interactor.handle(request);
        verify(gateway).getStudent(UUID.fromString(request.studentId));
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void interactorReportsEnrollment() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        course.setInstructor(instructor);
        interactor.handle(request);
        ArgumentCaptor<Enrollment> captor = ArgumentCaptor.forClass(Enrollment.class);
        verify(gateway).saveEnrollment(captor.capture());
        Enrollment savedEnrollment = captor.getValue();
        verify(gateway).getStudent(UUID.fromString(request.studentId));
        verify(gateway).getCourse(UUID.fromString(request.courseId));
        verify(gateway).getInstructor(UUID.fromString(request.instructorId));
        assertSame(student, savedEnrollment.student);
        assertSame(course, savedEnrollment.course);
    }

    @Test
    public void interactorDoesNotEnrollStudentAlreadyEnrolledInCourse() {
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        course.setInstructor(instructor);
        course.addEnrollment(new Enrollment(course, student));
        interactor.handle(request);
        verify(gateway, never()).saveEnrollment(any());
        verify(presenter).reportError(EXISTING_ENROLLMENT);
    }
}
