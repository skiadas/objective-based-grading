package obg.presenter;

import obg.core.entity.AttemptList;

public interface ViewAssignedAttemptsPresenter {
    void reportError(String errorMessage);
    void presentAssignedAttempts(AttemptList map);
}
