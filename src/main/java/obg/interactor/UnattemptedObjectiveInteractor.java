package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.UnattemptedObjectiveGateway;
import obg.request.UnattemptedObjectiveRequest;

import java.util.List;

public class UnattemptedObjectiveInteractor {

    private final UnattemptedObjectiveGateway gateway;

    public UnattemptedObjectiveInteractor(UnattemptedObjectiveGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(UnattemptedObjectiveRequest request, Presenter presenter){
        if (gateway.getStudent(request.studentId) == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else if (gateway.getCourse(request.courseId) == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (!gateway.getStudentIsEnrolled(request.studentId, request.courseId)) {
            presenter.reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
        } else if (gateway.getEnrollment(request.courseId, request.studentId ) == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        }
            else {
            List<String> objectiveList = gateway.getUnattemptedObjectives(request.studentId, request.courseId);
            presenter.presentUnattemptedObjectives(objectiveList);
        }
    }
}
