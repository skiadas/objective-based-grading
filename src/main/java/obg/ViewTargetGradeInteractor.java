package obg;

public class ViewTargetGradeInteractor {
    private final ViewTargetGradeGateway gateway;
    private Course course;

    public ViewTargetGradeInteractor(ViewTargetGradeGateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        course = gateway.getCourse(request.courseId);
        TargetGradeRequirementsResponse targetGradeRequirementsResponse = new TargetGradeRequirementsResponse(request.letterGrade);
        if (course == null) {
            return ErrorResponse.invalidCourse();
        } else if (!gateway.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGrade();
        }
        TargetGradeRequirementsResponse.objectiveRequirements = course.gradeBreaks.get(request.letterGrade);
        return targetGradeRequirementsResponse;
    }
}
