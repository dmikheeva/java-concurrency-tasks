package leetcode;

import java.util.concurrent.CountDownLatch;

/**
 * https://leetcode.com/problems/print-in-order/
 */
public class PrintInOrder {

    static class Foo {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            countDownLatch.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (countDownLatch.getCount() != 2) {
                //countDownLatch.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            countDownLatch.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (countDownLatch.getCount() != 1) {
                //countDownLatch.await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();

            countDownLatch.countDown();
        }
    }

    static class MyThread1 extends Thread {
        private Foo foo;
        private Runnable runnable;
        private int num;

        public MyThread1(Foo foo, Runnable runnable, int num) {
            this.foo = foo;
            this.runnable = runnable;
            this.num = num;
        }

        public void run() {
            try {
                switch (num) {
                    case 1:
                        foo.first(runnable);
                        break;
                    case 2:
                        foo.second(runnable);
                        break;
                    case 3:
                        foo.third(runnable);
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Runnable r1 = () -> System.out.println("printFirst");
        Runnable r2 = () -> System.out.println("printSecond");
        Runnable r3 = () -> System.out.println("printThird");
        new MyThread1(foo, r1, 1).start();
        new MyThread1(foo, r2, 2).start();
        new MyThread1(foo, r3, 3).start();
    }
}
