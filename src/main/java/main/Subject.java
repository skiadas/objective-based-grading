package main;

import java.util.ArrayList;
import java.util.List;

public class Subject<T> {
    List<Observer<T>> observers = new ArrayList<>();

    void notifyObservers(T value) {
        observers.forEach(o -> o.update(value));
    }

    void addObserver(Observer<T> o) {
        if (!observers.contains(o)) observers.add(o);
    }

    void removeObserver(Observer<T> o) {
        observers.remove(o);
    }
}
