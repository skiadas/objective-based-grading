package obg.interactor;

import obg.core.ErrorResponse;
import obg.core.entity.Instructor;
import obg.gateway.CreateNewInstructorGateway;
import obg.presenter.CreateNewInstructorPresenter;
import obg.request.CreateNewInstructorRequest;

public class CreateNewInstructorInteractor {
    private CreateNewInstructorGateway gateway;

    public CreateNewInstructorInteractor(CreateNewInstructorGateway gateway) {

        this.gateway = gateway;
    }

    public void handle(CreateNewInstructorRequest request, CreateNewInstructorPresenter presenter) {
        String instructorId= request.instructorId;
        String first = request.first;
        String last = request.last;
        if(gateway.getInstructor(instructorId) != null){
            presenter.reportError(ErrorResponse.INVALID_INSTRUCTOR);
        }else{
            Instructor instructor = new Instructor(instructorId, first, last);
            gateway.save(instructor);
            presenter.createNewInstructor(instructor);
        }
    }
}
