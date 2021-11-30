package main;

public interface Observer<T> {
    void update(T data);
}
