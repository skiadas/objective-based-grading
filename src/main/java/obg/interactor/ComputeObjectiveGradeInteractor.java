package obg.interactor;

import obg.core.Presenter;
import obg.core.Request;
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
        Course course = gateway.getCourse(UUID.fromString(request.courseId));
    }
}
