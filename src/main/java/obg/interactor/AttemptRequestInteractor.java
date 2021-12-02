package obg.interactor;
import obg.core.Interactor;
import obg.core.Presenter;
import obg.core.entity.Attempt;
import obg.core.entity.AttemptStatus;
import obg.core.entity.Course;
import obg.core.entity.Student;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;

public class AttemptRequestInteractor implements Interactor {

    private final AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(AttemptRequestRequest request, Presenter presenter) {
        Course course = gateway.getCourse(request.courseID);
        Student student = gateway.getStudent(request.userName);
        if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (student == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        } else if (!gateway.getStudentIsEnrolled(request.userName, request.courseID)) {
            presenter.reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
        }

        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), student, course, AttemptStatus.PENDING);
        presenter.presentAttemptCreated(attempt);
    }

}
