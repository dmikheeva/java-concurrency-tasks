package leetcode;

/**
 * https://leetcode.com/problems/print-foobar-alternately/
 */
public class PrintFooBarAlternately {

    static class FooBar {
        private int n;
        private volatile boolean fooFlag = true;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    if (!fooFlag) {
                        this.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    fooFlag = false;
                    this.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                synchronized (this) {
                    if (fooFlag) {
                        this.wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    fooFlag = true;
                    this.notify();
                }

            }
        }
    }

    static class MyThread extends Thread {
        private FooBar fooBar;
        private Runnable runnable;
        private int num;

        public MyThread(FooBar fooBar, Runnable runnable, int num) {
            this.fooBar = fooBar;
            this.runnable = runnable;
            this.num = num;
        }

        public void run() {
            try {
                switch (num) {
                    case 1:
                        fooBar.foo(runnable);
                        break;
                    case 2:
                        fooBar.bar(runnable);
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FooBar foo = new FooBar(5);
        Runnable r1 = () -> System.out.print("foo");
        Runnable r2 = () -> System.out.print("bar");
        new MyThread(foo, r1, 1).start();
        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        new MyThread(foo, r2, 2).start();
    }
}
