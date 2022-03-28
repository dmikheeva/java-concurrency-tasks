package leetcode;

import java.util.function.IntConsumer;

/**
 * https://leetcode.com/problems/print-zero-even-odd/
 */
public class ZeroEvenOddTask {

    public static class ZeroEvenOdd {
        private int n;
        private short mode;// 0 - print zero, 1 - print even, 2 - print odd
        private volatile int counter = 1;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (mode != 0) {
                        this.wait();
                    }
                    printNumber.accept(0);
                    mode = counter % 2 == 0 ? (short) 1 : (short) 2;
                    this.notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n / 2; i++) {
                synchronized (this) {
                    while (mode != 1) {
                        this.wait();
                    }
                    //if (counter <= n && counter % 2 == 0) {
                    printNumber.accept(counter);
                    counter++;
                    mode = 0;
                    //}
                    this.notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < (n + 1) / 2; i++) {
                synchronized (this) {
                    while (mode != 2) {
                        this.wait();
                    }
                    //if (counter <= n && counter % 2 != 0) {
                    printNumber.accept(counter);
                    counter++;
                    mode = 0;
                    //}
                    this.notifyAll();
                }
            }
        }
    }

    static class MyThread extends Thread {
        ZeroEvenOdd zeroEvenOdd;
        int num;
        IntConsumer consumer = System.out::print;

        MyThread(ZeroEvenOdd zeroEvenOdd, int num) {
            this.zeroEvenOdd = zeroEvenOdd;
            this.num = num;
        }

        public void run() {
            try {
                switch (num) {
                    case 1:
                        zeroEvenOdd.zero(consumer);
                        break;
                    case 2:
                        zeroEvenOdd.even(consumer);
                        break;
                    case 3:
                        zeroEvenOdd.odd(consumer);
                        break;
                }
            } catch (InterruptedException ex) {

            }
        }
    }

    public static void main(String[] args) {
        int n = 9;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);

        new MyThread(zeroEvenOdd, 1).start();
        new MyThread(zeroEvenOdd, 3).start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new MyThread(zeroEvenOdd, 2).start();
    }
}
