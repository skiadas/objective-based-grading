package obg;

import obg.mocks.GatewayTestDummy;

import java.util.UUID;

public class TargetGradeRequirementsGateway extends GatewayTestDummy {
    @Override
    public Course getCourse(UUID courseId) {
        return new Course(UUID.randomUUID(), "course1");
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        return true;
    }

}
