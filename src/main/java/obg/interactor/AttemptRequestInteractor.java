package obg.interactor;
import obg.core.Interactor;
import obg.core.Presenter;
import obg.core.entity.*;
import obg.gateway.AttemptRequestGateway;
import obg.request.AttemptRequestRequest;
import obg.core.ErrorResponse;

public class AttemptRequestInteractor implements Interactor {

    private final AttemptRequestGateway gateway;

    public AttemptRequestInteractor(AttemptRequestGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(AttemptRequestRequest request, Presenter presenter) {
//        Course course = gateway.getCourse(request.courseID);
//        Student student = gateway.getStudent(request.studentID);
        Enrollment enrollment = gateway.getEnrollment(request.courseID, request.studentID);
//        if (course == null) {
//            presenter.reportError(ErrorResponse.INVALID_COURSE);
//        } else if (student == null) {
//            presenter.reportError(ErrorResponse.INVALID_STUDENT);
//        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
//            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
//        } else if (!gateway.getStudentIsEnrolled(request.studentID, request.courseID)) {
//            presenter.reportError(ErrorResponse.STUDENT_NOT_ENROLLED);
//        }
//        if (enrollment == null) {
//            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
//        } else
        if (gateway.getEnrolledStudent() == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if  (gateway.getEnrolledCourse() == null){
            presenter.reportError(ErrorResponse.INVALID_ENROLLMENT);
        } else if (!gateway.objectiveInCourse(request.objective, request.courseID)) {
            presenter.reportError(ErrorResponse.INVALID_OBJECTIVE);
        }

        Attempt attempt = new Attempt(request.objective, gateway.getAttemptNumber(), enrollment.student, enrollment.course);
        presenter.presentAttemptCreated(attempt);
    }

}
