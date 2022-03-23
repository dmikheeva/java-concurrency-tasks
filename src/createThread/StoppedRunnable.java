package createThread;

public class StoppedRunnable implements Runnable {

    private boolean doStop = false;

    public synchronized void doStop() {
        doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            System.out.println("Running");

            try {
                Thread.sleep(1L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StoppedRunnable runnable = new StoppedRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            Thread.sleep(10L * 1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnable.doStop();
    }
}

