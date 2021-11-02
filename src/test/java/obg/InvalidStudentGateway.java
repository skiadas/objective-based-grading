package obg;

import java.util.ArrayList;
import java.util.UUID;

public class InvalidStudentGateway implements AttemptRequestGateway {
    public Student student;
    public Course course;
    public ArrayList<String> objectives;

    @Override
    public Course getCourse(UUID courseId) {

        course = new Course(courseId, null, null, null);
        return course;
    }

    public Student getStudent(Student givenStudent) {
        return student;
    }



}
