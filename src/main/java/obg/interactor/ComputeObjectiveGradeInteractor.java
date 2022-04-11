package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.gateway.ComputeObjectiveGradeGateway;
import obg.request.ComputeObjectiveGradeRequest;

import java.util.UUID;

public class ComputeObjectiveGradeInteractor {
    private final ComputeObjectiveGradeGateway gateway;
    private final Presenter presenter;

    public ComputeObjectiveGradeInteractor(ComputeObjectiveGradeGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(ComputeObjectiveGradeRequest request) {
        if (gateway.getEnrollment(request.courseId, request.studentId) == null) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (gateway.getEnrollment(request.courseId, request.studentId).attemptMap.isEmpty()) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else {
            int objGrade = gateway.getEnrollment(request.courseId, request.studentId).computeObjectiveGrade(request.obj);
            presenter.presentObjectiveGrade(objGrade);
        }
    }
}
