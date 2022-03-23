package singleton.nonLazy;

public class StaticFieldSingleton {
    private static StaticFieldSingleton singleton = new StaticFieldSingleton();

    public static StaticFieldSingleton getInstance() {
        return singleton;
    }

    private StaticFieldSingleton() {
    }
}
