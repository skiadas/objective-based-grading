package obg.interactor;

import obg.core.Presenter;
import obg.core.entity.Course;
import obg.gateway.StudentCourseListGateway;
import obg.request.StudentCourseListRequest;
import obg.core.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseListInteractor {
    StudentCourseListGateway gateway;
    public StudentCourseListInteractor(StudentCourseListGateway gateway) {
        this.gateway = gateway;
    }


    public void handle(StudentCourseListRequest request, Presenter presenter){
        if (gateway.getStudent(request.userName) == null) {
            presenter.reportError(ErrorResponse.invalidStudent());
        } else {
            // TODO: You'll need to create the courses
            List<Course> courses = new ArrayList<>();
            presenter.presentStudentCourseList(courses);
        }
    }
}
