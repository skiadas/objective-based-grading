package obg.presenter;

import obg.core.entity.AttemptList;
import obg.core.entity.AttemptMap;

public interface ViewAssignedAttemptsPresenter {
    void reportError(String errorMessage);
    void presentAssignedAttempts(AttemptList map);
}
