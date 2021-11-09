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
        } else if (!course.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGrade();
        }
        return new TargetGradeRequirementsResponse(request.letterGrade, course.gradeBreaks.get(request.letterGrade));
    }
}
