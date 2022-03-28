package obg.gateway;

import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;

import java.util.UUID;

public interface addStudentToCourseGateway {
    Instructor getInstructor(UUID instructorId);
    Course getCourse(UUID courseId);
    Student getStudent(UUID studentId);

    void saveEnrollment(Enrollment enrollment);
}
