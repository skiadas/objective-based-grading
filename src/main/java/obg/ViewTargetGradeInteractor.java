package obg;

public class ViewTargetGradeInteractor {
    private Gateway gateway;
    private String lettergrade;

    public ViewTargetGradeInteractor(Gateway gateway) {
        this.gateway = gateway;
    }

    public Response handle(ViewTargetGradeRequest request) {
        Course course = gateway.getCourse(request.courseId);
        lettergrade = request.letterGrade;
        if (course == null) {
            return ErrorResponse.invalidCourseError();
        } else if (!gateway.isValidLetterGrade(request.letterGrade)) {
            return ErrorResponse.invalidLetterGradeError();
        }
        return null;
    }

}
