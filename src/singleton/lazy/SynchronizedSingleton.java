package singleton.lazy;

public class SynchronizedSingleton {
    private static SynchronizedSingleton singleton;

    public synchronized SynchronizedSingleton getInstance() {
        if (singleton == null) {
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }
}
