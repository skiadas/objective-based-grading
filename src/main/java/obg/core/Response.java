package obg.core;

public interface Response<T> {
    T getValues();
    String getErrorMessage();
    boolean isSuccessful();
}
