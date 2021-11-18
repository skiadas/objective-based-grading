package obg.gateway;

import obg.core.entity.Attempt;
import obg.core.entity.Course;
import obg.core.entity.Instructor;

import java.util.List;
import java.util.UUID;

public interface ViewPendingAttemptsGateway {

    Instructor getInstructor(UUID instructorId);

    Course getCourse(UUID courseId);

    List<Attempt> getAttempts(Course course);
}
