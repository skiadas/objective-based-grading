package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.ViewPendingAttemptsGateway;
import obg.request.ViewPendingAttemptsRequest;

public class ViewPendingAttemptsInteractor {

    ViewPendingAttemptsGateway gateway;

    public ViewPendingAttemptsInteractor(ViewPendingAttemptsGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ViewPendingAttemptsRequest request, Presenter presenter) {
        Instructor instructor = gateway.getInstructor(request.instructorId);
        Course course = gateway.getCourse(request.courseId);
        if (instructor == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        }
        if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (!course.isCourseInstructor(instructor)) {
            presenter.reportError(ErrorResponse.INVALID_COURSE_INSTRUCTOR);
        }
        else {
            presenter.presentPendingAttempts(gateway.getAttempts(course));
        }
    }
}