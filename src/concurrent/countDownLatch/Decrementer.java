package concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

public class Decrementer implements Runnable {

    private CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            long n = latch.getCount();
            System.out.println("Latch count " + latch.getCount());
            for (int i = 0; i < n; i++) {
                latch.countDown();
                System.out.println("Latch count " + latch.getCount());
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
