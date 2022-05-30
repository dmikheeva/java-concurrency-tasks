package concurrent.countDownLatch.waiterDecrementer;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(new Waiter(latch)).start();
        new Thread(new Decrementer(latch)).start();

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
