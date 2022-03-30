package obg;

import obg.core.*;
import obg.gateway.Gateway;
import obg.interactor.AttemptRequestInteractor;
import obg.interactor.InstructorCourseListInteractor;
import obg.interactor.StudentCourseListInteractor;
import obg.request.AttemptRequestRequest;
import obg.request.InstructorCourseListRequest;
import obg.request.StudentCourseListRequest;

import java.util.UUID;

public class ConcreteAppContext implements AppContext {
    private final GatewayFactory gatewayFactory;

    public ConcreteAppContext(GatewayFactory gatewayFactory) {
        this.gatewayFactory = gatewayFactory;
    }

    public void instructorCourseListRequested(String instructorId, Presenter presenter) {
        InstructorCourseListRequest request = new InstructorCourseListRequest(instructorId);
        InstructorCourseListInteractor interactor = new InstructorCourseListInteractor(getGateway());
        interactor.handle(request, presenter);
    }

    public void studentCourseListRequested(String studentId, Presenter presenter) {
        StudentCourseListRequest request = new StudentCourseListRequest(studentId);
        StudentCourseListInteractor interactor = new StudentCourseListInteractor(getGateway());
    }

    public void attemptRequested(String studentId, String courseId, String objective, Presenter presenter) {
        AttemptRequestRequest request = new AttemptRequestRequest(studentId, UUID.fromString(courseId), objective);
        AttemptRequestInteractor interactor = new AttemptRequestInteractor(getGateway());
        interactor.handle(request, presenter);
    }

    private Gateway getGateway() {
        return gatewayFactory.acquireGateway();
    }
}
