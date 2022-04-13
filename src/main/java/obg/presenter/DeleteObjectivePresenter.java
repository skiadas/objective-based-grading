package obg.presenter;

public interface DeleteObjectivePresenter {
    void reportError(String invalidObjective);

    void reportObjectDeletedMessage(String deletedObject);
}
