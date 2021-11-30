package main;

// Subject
public class Clock extends Subject<Integer> {
    private static final Clock instance = new Clock();
    private int times = 0;

    static Clock getInstance() {
        return instance;
    }

    private Clock() {
        new Thread(this::monitor).start();
    }

    private void monitor() {
        while (true) {
            tick();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    void tick() {
        times++;
        notifyObservers(times);
    }

}
