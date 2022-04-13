package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Attempt;
import obg.core.entity.Instructor;
import obg.gateway.InstructorCanDeleteAttemptGateway;
import obg.presenter.InstructorCanDeleteAttemptPresenter;
import obg.request.InstructorCanDeleteAttemptRequest;

import java.util.UUID;

public class InstructorCanDeleteAttemptInteractor {
    private final InstructorCanDeleteAttemptGateway gateway;

    public InstructorCanDeleteAttemptInteractor(InstructorCanDeleteAttemptGateway gateway) {
        this.gateway = gateway;
    }

    public void handle(InstructorCanDeleteAttemptRequest request, InstructorCanDeleteAttemptPresenter presenter) {
        UUID instructorUUID = UUIDFromString(request.instructorId);
        Instructor instructor = gateway.getInstructor(instructorUUID);
        UUID attemptUUID = UUIDFromString(request.attemptId);
        Attempt attempt = gateway.getAttempt(attemptUUID);
        if (instructor == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else if (attempt == null) {
            presenter.reportError(ErrorResponse.INVALID_ATTEMPT);
        } else if (!attempt.isEnrollmentCourseInstructor(instructor)) {
            presenter.reportError((ErrorResponse.NOT_COURSE_INSTRUCTOR));
        } else {
            gateway.removeAttempt(attempt.getLongId());

            presenter.presentSuccessfulRemove("Successfully Removed");
        }
    }

    private UUID UUIDFromString(String stringId) {
        return UUID.fromString(stringId);
    }
}
