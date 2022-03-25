package obg.presenter;

import obg.core.entity.Instructor;

public interface CreateNewInstructorPresenter {
    void reportError(String errorMessage);
    void createNewInstructor(Instructor instructor);
}
