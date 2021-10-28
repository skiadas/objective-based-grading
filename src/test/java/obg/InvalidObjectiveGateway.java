package obg;

import obg.mocks.GatewayTestDummy;

import java.util.List;

public class InvalidObjectiveGateway extends GatewayTestDummy {
    public InvalidObjectiveGateway(List<String> objectives) {
        this.objectives = objectives;
    }

    private List<String> objectives;

    public boolean isValidObjective(String givenObjective){
        return objectives.contains(givenObjective);
    }

    public boolean isValidCourse(Course course1) {
        return true;
    }

    public boolean isValidStudent(Student student) {
        return true;
    }
}
