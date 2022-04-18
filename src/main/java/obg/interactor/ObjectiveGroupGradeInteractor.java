package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.gateway.ObjectiveGroupGradeGateway;
import obg.request.ObjectiveGroupGradeRequest;

public class ObjectiveGroupGradeInteractor {

    private final ObjectiveGroupGradeGateway gateway;
    private final Presenter presenter;

    public ObjectiveGroupGradeInteractor(ObjectiveGroupGradeGateway gateway, Presenter presenter) {
        this.presenter = presenter;
        this.gateway = gateway;
    }

    public void handle(ObjectiveGroupGradeRequest request) {
        if (gateway.getEnrollment(request.courseID, request.studentID ) == null) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        }
        else {
            int objectiveGroupGrade = gateway.getEnrollment( request.courseID, request.studentID).computeObjectiveGroupGrade(request.objGroup);
            presenter.presentObjectiveGroupGrade(objectiveGroupGrade);
        }
    }
}
