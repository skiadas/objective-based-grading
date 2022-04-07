package obg.presenter;

public interface StudentViewRemainingAttemptsPresenter {

    void reportError(String errorMessage);

    void presentRemainingAttempts(int attempts);
}
