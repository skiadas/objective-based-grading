package obg;

public class ViewTargetGradeInteractor {
    private ViewTargetGradeGateway gateway;
    private String lettergrade;

    public ViewTargetGradeInteractor(ViewTargetGradeGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        Course course = gateway.getCourse(request.courseId);
        lettergrade = request.letterGrade;
        if (course == null) {
            return ErrorResponse.invalidCourse();
        } else if (!gateway.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGrade();
        }
        return new RequirementsResponse(request.letterGrade);
    }

}
