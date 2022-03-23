package example;

public class Counter {

    protected long count = 0;

    public void add(long value) {
        this.count = this.count + value;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {counter.add(2);});
            Thread t2 = new Thread(() -> {counter.add(3);});
            t1.start();
            t2.start();
            System.out.println(counter.count);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.count=0;
        }
    }
}
