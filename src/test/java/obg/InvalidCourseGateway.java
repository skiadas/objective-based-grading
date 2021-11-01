package obg;

import obg.mocks.GatewayTestDummy;

import java.util.UUID;

public class InvalidCourseGateway implements ViewTargetGradeGateway {
    UUID providedCourseId;

    @Override
    public Course getCourse(UUID courseId) {
        providedCourseId = courseId;
        return null;
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }
}
