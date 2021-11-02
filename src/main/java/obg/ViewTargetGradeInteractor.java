package obg;

public class ViewTargetGradeInteractor {
    private final ViewTargetGradeGateway gateway;

    public ViewTargetGradeInteractor(ViewTargetGradeGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        Course course = gateway.getCourse(request.courseId);
        if (course == null) {
            return ErrorResponse.invalidCourse();
        } else if (!gateway.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGrade();
        }
        return new RequirementsResponse(request.letterGrade);
    }

}
