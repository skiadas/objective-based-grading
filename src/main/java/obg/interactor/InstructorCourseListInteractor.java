package obg.interactor;

import obg.core.Presenter;
import obg.core.ErrorResponse;
import obg.core.Interactor;
import obg.core.entity.Course;
import obg.core.entity.Instructor;
import obg.gateway.InstructorCourseListGateway;
import obg.request.InstructorCourseListRequest;

import java.util.List;

public class InstructorCourseListInteractor implements Interactor {
    private final InstructorCourseListGateway gateway;

    public InstructorCourseListInteractor(InstructorCourseListGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(InstructorCourseListRequest request, Presenter presenter) {
        // Ask the gateway if instructor exists
        // TODO: Can we do this better?
        Instructor instr = gateway.getInstructor(request.instructorId);
        if (instr == null) {
            presenter.reportError(ErrorResponse.invalidInstructor());
        } else {
            List<Course> courses = gateway.getCoursesTaughtBy(instr);
            presenter.presentInstructorCourseList(courses);
        }
    }
}
