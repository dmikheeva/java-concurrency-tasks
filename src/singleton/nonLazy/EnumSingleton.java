package singleton.nonLazy;

public enum EnumSingleton {
    INSTANCE;

    public void doWork() {
        System.out.println("enum singleton working...");
    }
}
