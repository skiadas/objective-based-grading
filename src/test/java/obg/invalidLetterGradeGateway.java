package obg;

import obg.mocks.GatewayTestDummy;

import java.util.List;
import java.util.UUID;

public class invalidLetterGradeGateway extends GatewayTestDummy {
    public String providedLetterGrade;

    @Override
    public Course getCourse(UUID courseId) {
        return new Course(UUID.randomUUID(), "course1");
    }

    @Override
    public boolean isValidLetterGrade(String letterGrade) {
        providedLetterGrade = letterGrade;
        return false;
    }

    @Override
    public Instructor getInstructor(UUID instructorId) {
        return null;
    }

    @Override
    public List<Course> getCoursesTaughtBy(Instructor instructor) {
        return null;
    }
}
