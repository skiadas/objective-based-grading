package obg;

import obg.mocks.GatewayTestDummy;

import java.util.UUID;

public class TargetGradeRequirementsGateway extends GatewayTestDummy {
    @Override
    public Course getCourse(UUID courseId) {
        return null;
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return false;
    }
}
