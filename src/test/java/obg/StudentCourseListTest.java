package obg;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.StudentCourseListGateway;
import obg.interactor.StudentCourseListInteractor;
import obg.request.StudentCourseListRequest;
import obg.core.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentCourseListTest {

    private StudentCourseListGateway gateway;
    private StudentCourseListInteractor interactor;
    private StudentCourseListRequest request;
    private Presenter presenter;

    @Before
    public void setUp() {
        gateway = mock(StudentCourseListGateway.class);
        interactor = new StudentCourseListInteractor(gateway);
        request = new StudentCourseListRequest("userName");
        presenter = mock(Presenter.class);
    }

    @Test
    public void isInvalidStudent(){
        interactor.handle(request, presenter);
        verify(presenter).reportError(ErrorResponse.INVALID_STUDENT);
    }

    @Test
    public void InteractorAskGatewayForCorrectStudent(){
        interactor.handle(request, presenter);
        verify(gateway).getStudent(request.userName);
    }

    @Test
    public void ReturnListOfStudentCourses(){
        List<Course> courseList = MakeCourses();
        Student newStudent = new Student(UUID.randomUUID(), request.userName);
        when(gateway.getStudent(request.userName)).thenReturn(newStudent);
        when(gateway.getStudentCourses(request.userName)).thenReturn(courseList);
        interactor.handle(request, presenter);
        verify(gateway).getStudent(request.userName);
        verify(gateway).getStudentCourses(request.userName);
        verify(presenter).presentStudentCourseList(courseList);
    }

    @Test
    public void removeStudentFromCourse() {
        Student student1 = new Student(UUID.randomUUID(), "James");
        Course course1 = new Course(UUID.randomUUID(), "course1");
        Course course2 = new Course(UUID.randomUUID(), "course2");
        StudentCourseListRequest newRequest = new StudentCourseListRequest(student1.userName);
        Instructor instructor = new Instructor(UUID.randomUUID().toString(), "first", "last");
        course1.setInstructor(instructor);
        course2.setInstructor(instructor);
        List<Course> courseListOfOne = List.of(course2);
        Enrollment enrollment = new Enrollment(course1, student1, "today", false);
        Enrollment enrollment2 = new Enrollment(course2, student1, "today", false);
        enrollment.removeStudent(instructor, student1.getStudentId(), course1.getCourseId());
        when(gateway.getStudent(newRequest.userName)).thenReturn(student1);
        when(gateway.getStudentCourses(student1.userName)).thenReturn(courseListOfOne);
        interactor.handle(newRequest, presenter);
        verify(gateway).getStudent(newRequest.userName);
        verify(gateway).getStudentCourses(newRequest.userName);
        verify(presenter).presentStudentCourseList(courseListOfOne);
    }

    private List<Course> MakeCourses() {
        Course course1 = new Course(null, null);
        Course course2 = new Course(null, null);
        Course course3 = new Course(null, null);
        return List.of(course1, course2, course3);
    }

}
