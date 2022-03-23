package concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class Decrementer implements Runnable {
    private Semaphore semaphore = null;
    private String name;

    public Decrementer(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + name + " waiting for acquire a semaphore");
            semaphore.acquire();
            System.out.println("Thread " + name + "  acquired a semaphore");

            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " releases a semaphore");
        semaphore.release();
    }
}
