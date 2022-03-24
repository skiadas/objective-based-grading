package obg.presenter;

public interface InstructorCanDeleteAttemptPresenter {
    void reportError(String errorMessage);

    void presentSuccessfulRemove(String message);
}
