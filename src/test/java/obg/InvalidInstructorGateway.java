package obg;

import obg.mocks.GatewayTestDummy;

import java.util.UUID;

public class InvalidInstructorGateway extends GatewayTestDummy {
    UUID givenInstructorId;

    public Instructor getInstructor(UUID instructorId) {
        givenInstructorId = instructorId;
        return null;
    }
}
