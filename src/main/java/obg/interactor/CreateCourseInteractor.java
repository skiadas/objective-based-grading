package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.CreateCourseGateway;
import obg.presenter.CreateCoursePresenter;
import obg.request.CreateCourseRequest;

import java.util.UUID;

public class CreateCourseInteractor {
    private final CreateCourseGateway gateway;

    public CreateCourseInteractor(CreateCourseGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(CreateCourseRequest request, CreateCoursePresenter presenter) {
        Instructor instructor = gateway.getInstructor(request.instructorId);
        if (instructor == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else {
            Course course = new Course(request.courseName);
            course.setInstructor(instructor);
            gateway.save(course);
            presenter.reportCourseCreated(course);
        }
    }
}
