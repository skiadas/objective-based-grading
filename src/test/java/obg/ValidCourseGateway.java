package obg;

import obg.mocks.GatewayTestDummy;

import java.util.UUID;

public class ValidCourseGateway extends GatewayTestDummy {
    Course course = new Course(UUID.randomUUID(), "CS321");

    @Override
    public Course getCourse(UUID courseId) {
        return course;
    }

}
