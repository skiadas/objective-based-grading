package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.ObjectiveGroup;
import obg.core.entity.Student;
import obg.gateway.ObjectiveGroupGradeGateway;
import obg.presenter.ObjectiveGroupGradePresenter;
import obg.request.ObjectiveGroupGradeRequest;

import java.util.UUID;

public class ObjectiveGroupGradeInteractor {

    private final ObjectiveGroupGradeGateway gateway;

    public ObjectiveGroupGradeInteractor(ObjectiveGroupGradeGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ObjectiveGroupGradeRequest request, ObjectiveGroupGradePresenter presenter) {
        Student student = gateway.getStudent(request.studentID);
        Course course = gateway.getCourse(request.courseID);
        ObjectiveGroup group = request.group;
        if (student == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        }
        else if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        }
        else if (!course.isValidObjectiveGroup(group)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        }
    }
}