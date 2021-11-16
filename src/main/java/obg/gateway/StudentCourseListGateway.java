package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Student;

import java.util.List;

public interface StudentCourseListGateway {
    Student getStudent(String userName);

    List<Course> getStudentCourses(String userName);
}
