package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.addStudentToCourseGateway;
import obg.request.addStudentToCourseRequest;

import java.util.UUID;

public class addStudentToCourseInteractor {
    private final addStudentToCourseGateway gateway;
    private final Presenter presenter;

    public addStudentToCourseInteractor(addStudentToCourseGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(addStudentToCourseRequest request) {
        Instructor instructor = gateway.getInstructor(UUID.fromString(request.instructorId));
        Course course = gateway.getCourse(UUID.fromString(request.courseId));
        Student student = gateway.getStudent(UUID.fromString(request.studentId));
        if (instructor == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (!course.isCourseInstructor(instructor)) {
            presenter.reportError(ErrorResponse.NOT_COURSE_INSTRUCTOR);
        } else if (student == null) {
            Enrollment enrollment = new Enrollment(course, student);
            gateway.saveEnrollment(enrollment);
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        } else {
            course.addStudent(student);
        }
    }
}
