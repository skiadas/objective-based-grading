package obg;

import java.util.UUID;

public class ViewTargetGradeInteractor {
    private Gateway gateway;
    public ViewTargetGradeInteractor(Gateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        gateway.getCourse(request.courseId);
        return ErrorResponse.invalidCourseError();
    }
}
