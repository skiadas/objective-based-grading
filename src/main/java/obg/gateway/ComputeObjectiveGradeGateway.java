package obg.gateway;


import obg.core.entity.Course;

import java.util.UUID;

public class ComputeObjectiveGradeGateway {
    public final String courseId;

    public ComputeObjectiveGradeGateway(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse(UUID courseId) {
        return null;
    }
}
