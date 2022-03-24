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


    private List<Course> MakeCourses() {
        Course course1 = new Course(null, null);
        Course course2 = new Course(null, null);
        Course course3 = new Course(null, null);
        return List.of(course1, course2, course3);
    }

}
