package obg;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.addStudentToCourseGateway;
import obg.interactor.addStudentToCourseInteractor;
import obg.request.addStudentToCourseRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class addStudentToCourseTest {

    private String instructorId;
    private String courseId;
    private String studentId;
    private Presenter presenter;
    private addStudentToCourseRequest request;
    private addStudentToCourseGateway gateway;
    private addStudentToCourseInteractor interactor;
    private Instructor instructor;
    private Course course;
    private Student student;

    @Before
    public void setUp() throws Exception {
        instructorId = UUID.randomUUID().toString();
        courseId = UUID.randomUUID().toString();
        studentId = UUID.randomUUID().toString();
        presenter = mock(Presenter.class);
        request = new addStudentToCourseRequest(instructorId, courseId, studentId);
        gateway = mock(addStudentToCourseGateway.class);
        interactor = new addStudentToCourseInteractor(gateway, presenter);
        instructor = new Instructor(instructorId, "first", "last");
        course = new Course(UUID.randomUUID(), "course");
        student = new Student(UUID.randomUUID(), "student");
    }

    @Test
    public void canCreateAddStudentToCourseRequest() {
        String instructorId = "InstructorId";
        String courseId = "courseId";
        String studentId = "studentId";
        addStudentToCourseRequest request = new addStudentToCourseRequest(instructorId, courseId, studentId);
        assertEquals(instructorId, request.instructorId);
        assertEquals(courseId, request.courseId);
        assertEquals(studentId, request.studentId);
    }

    @Test
    public void interactorReportsInstructorDoesNotExist() {
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getInstructor(UUID.fromString(request.instructorId));
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
    }

    @Test
    public void interactorReportsInvalidCourse() {
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(null);
        interactor.handle(request);
        verify(gateway).getCourse(UUID.fromString(request.courseId));
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE);
    }

    @Test
    public void interactorReportsNotCourseInstructor() {
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        interactor.handle(request);
        verify(presenter).reportError(ErrorResponse.INVALID_COURSE_INSTRUCTOR);
    }


    // TEST 5:
    // Student student = gateway.getStudent(request.studentId);
    // If (student == null) { presenter.reportError(INVALID_STUDENT); }
    @Test
    public void interactorReportsInvalidStudent() {
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        course.setInstructor(instructor);
        interactor.handle(request);
        verify(gateway).getStudent(UUID.fromString(request.studentId));
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    // Test 6:
    // When all checks are successful, student is added to course!
    @Test
    public void interactorAddsStudentToCourse() {
        when(gateway.getInstructor(UUID.fromString(request.instructorId))).thenReturn(instructor);
        when(gateway.getCourse(UUID.fromString(request.courseId))).thenReturn(course);
        course.setInstructor(instructor);
        when(gateway.getStudent(UUID.fromString(request.studentId))).thenReturn(student);
        interactor.handle(request);
        assertTrue(course.students.contains(student));
    }
}
