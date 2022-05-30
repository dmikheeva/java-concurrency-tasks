package concurrent.cyclicBarrier.ex1;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Runnable barrierAction = () -> System.out.println("barrier executed!");
        CyclicBarrier barrier1= new CyclicBarrier(2, barrierAction);

        CyclicBarrierRunnable r = new CyclicBarrierRunnable(barrier1);
        new Thread(r).start();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r).start();

    }
}
