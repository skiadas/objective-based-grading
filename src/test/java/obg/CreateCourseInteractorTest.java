package obg;

import obg.core.ErrorResponse;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.CreateCourseGateway;
import obg.interactor.CreateCourseInteractor;
import obg.presenter.CreateCoursePresenter;
import obg.request.CreateCourseRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateCourseInteractorTest {

    private CreateCourseGateway gateway;
    private CreateCourseInteractor interactor;
    private CreateCourseRequest request;
    private CreateCoursePresenter presenter;

    @Before
    public void setUp() throws Exception {
        gateway = mock(CreateCourseGateway.class);
        interactor = new CreateCourseInteractor(gateway);
        request = new CreateCourseRequest("skiadas", "my course name");
        presenter = mock(CreateCoursePresenter.class);
    }

    @Test
    public void whenCreatingCourseWithInvalidInstructorThenReportError() {
        when(gateway.getInstructor(request.instructorId)).thenReturn(null);
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(request.instructorId);
        verify(presenter).reportError(ErrorResponse.INVALID_INSTRUCTOR);
        verify(gateway, never()).save(any());
    }

    @Test
    public void whenCreatingCourseWithValidInstructorThenSaveItAndReportToPresenter() {
        Instructor instructor = new Instructor(request.instructorId);
        when(gateway.getInstructor(request.instructorId)).thenReturn(instructor);
        interactor.handle(request, presenter);
        verify(gateway).getInstructor(request.instructorId);
        ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
        verify(gateway).save(captor.capture());
        Course savedCourse = captor.getValue();
        assertEquals(request.courseName, savedCourse.courseName);
        assertSame(instructor, savedCourse.instructor);
        verify(presenter).reportCourseCreated(savedCourse);
    }
}
