package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.Presenter;
import obg.core.entity.Course;
import obg.core.entity.Enrollment;
import obg.core.entity.Instructor;
import obg.core.entity.Student;
import obg.gateway.AddStudentToCourseGateway;
import obg.request.AddStudentToCourseRequest;

import java.util.UUID;

public class AddStudentToCourseInteractor {
    private final AddStudentToCourseGateway gateway;
    private final Presenter presenter;

    public AddStudentToCourseInteractor(AddStudentToCourseGateway gateway, Presenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public void handle(AddStudentToCourseRequest request) {
        Instructor instructor = gateway.getInstructor(UUID.fromString(request.instructorId));
        Course course = gateway.getCourse(UUID.fromString(request.courseId));
        Student student = gateway.getStudent(UUID.fromString(request.studentId));
        if (instructor == null) {
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        } else if (course == null) {
            presenter.reportError(ErrorResponse.INVALID_COURSE);
        } else if (!course.isCourseInstructor(instructor)) {
            presenter.reportError(ErrorResponse.NOT_COURSE_INSTRUCTOR);
        } else if (course.hasStudent(student)) {
            presenter.reportError(ErrorResponse.EXISTING_ENROLLMENT);
        } else {
            Enrollment enrollment = new Enrollment(course, student);
            gateway.saveEnrollment(enrollment);
            presenter.reportError(ErrorResponse.INVALID_STUDENT);
        }
    }
}
