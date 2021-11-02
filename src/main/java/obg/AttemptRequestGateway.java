package obg;

import java.util.UUID;

public interface AttemptRequestGateway {
    Course getCourse(UUID courseId);

    Student getStudent(Student student);

}
