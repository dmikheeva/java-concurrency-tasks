package concurrent.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentNavigableMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(10);

        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();

        Thread.sleep(5000L);
    }
}
