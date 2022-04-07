package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Course;
import obg.core.entity.Student;
import obg.gateway.StudentViewRemainingAttemptsGateway;
import obg.presenter.StudentViewRemainingAttemptsPresenter;
import obg.request.StudentViewRemainingAttemptsRequest;

public class StudentViewRemainingAttemptsInteractor {

    StudentViewRemainingAttemptsGateway gateway;
    StudentViewRemainingAttemptsPresenter presenter;

    public StudentViewRemainingAttemptsInteractor(StudentViewRemainingAttemptsGateway gateway, StudentViewRemainingAttemptsPresenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(StudentViewRemainingAttemptsRequest request) {
        Student student = gateway.getStudent(request.studentId);
        Course course = gateway.getCourse(request.courseId);
        if (student == null) {
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        }
    }
}
