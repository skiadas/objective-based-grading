package obg.gateway;

import obg.core.entity.Course;

import java.util.UUID;

public interface ViewTargetGradeGateway {

    Course getCourse(UUID courseId);

}
