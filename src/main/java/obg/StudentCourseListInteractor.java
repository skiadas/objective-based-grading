package obg;

public class StudentCourseListInteractor {
    StudentCourseListGateway gateway;
    public StudentCourseListInteractor(StudentCourseListGateway gateway) {
        this.gateway = gateway;
    }


    Response handle(StudentCourseListRequest request){

        StudentCourseListResponse response = new StudentCourseListResponse();
        if(gateway.getStudent(request.userName) == null){
            return ErrorResponse.invalidStudent();
        }
        return response;

    }
}
