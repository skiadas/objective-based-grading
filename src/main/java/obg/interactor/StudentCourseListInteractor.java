package obg.interactor;

import obg.response.ErrorResponse;
import obg.response.StudentCourseListResponse;
import obg.core.Response;
import obg.gateway.StudentCourseListGateway;
import obg.request.StudentCourseListRequest;

public class StudentCourseListInteractor {
    StudentCourseListGateway gateway;
    public StudentCourseListInteractor(StudentCourseListGateway gateway) {
        this.gateway = gateway;
    }


    public Response handle(StudentCourseListRequest request){

        StudentCourseListResponse response = new StudentCourseListResponse();
        if(gateway.getStudent(request.userName) == null){
            return ErrorResponse.invalidStudent();
        }
        return response;

    }
}
