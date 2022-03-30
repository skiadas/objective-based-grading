package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.AttemptList;
import obg.core.entity.AttemptMap;
import obg.core.entity.Enrollment;
import obg.gateway.AssignedAttemptGateway;
import obg.presenter.ViewAssignedAttemptsPresenter;
import obg.request.ViewAssignedAttemtsRequest;
import obg.core.entity.Attempt;

import java.util.List;

public class ViewAssignedAttemptsInteractor {
    AssignedAttemptGateway gateway;

    public ViewAssignedAttemptsInteractor(AssignedAttemptGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(ViewAssignedAttemtsRequest request, ViewAssignedAttemptsPresenter presenter){
        Enrollment enrollment = gateway.getEnrollment(request.course, request.student);
        if(enrollment == null) {
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else {
            AttemptMap map = enrollment.getAttemptMap();
            AttemptList assignedList = new AttemptList();
            for (String obj: request.objectives) {
                if (map.getAttemptList(obj) != null) {
                    List<Attempt> thisList = map.getAttemptList(obj).list;
                    for (Attempt attempt : thisList) {
                        if (attempt.getStatus() == Attempt.AttemptStatus.ASSIGNED) {
                            assignedList.add(attempt);
                        }
                    }
                }
            }
            presenter.presentAssignedAttempts(assignedList);
        }
    }
}
