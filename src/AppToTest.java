import createThread.MyRunnable;
import singleton.nonLazy.EnumSingleton;


public class AppToTest {
    /*public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello1");
            }
        };
        (new Thread(runnable1)).start();

        Runnable runnable2 = () -> {
            System.out.println(Thread.currentThread().getName());
            //System.out.println("runnable2");
        };
        (new Thread( runnable2, "Thread2")).start();
    }*/

    /*public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }*/

    public static void main(String[] args) {
        EnumSingleton.INSTANCE.doWork();
    }
}
