package leetcode;

import java.util.Scanner;
import java.util.function.IntConsumer;

/**
 * <a href="https://leetcode.com/problems/fizz-buzz-multithreaded/">https://leetcode.com/problems/fizz-buzz-multithreaded/</a>
 */
public class FizzBuzzMultithreaded {
    static class FizzBuzz {
        private final int n;
        private FizzBuzzType flag = FizzBuzzType.NUM;
        private  int i = 1;

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            while (i < n +1) {
                synchronized (this) {
                    if (flag != FizzBuzzType.FIZZ) {
                        this.wait();
                    } else {
                        printFizz.run();
                        setFlag(i + 1);
                        i++;
                        this.notifyAll();
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (i < n +1) {
                synchronized (this) {
                    if (flag != FizzBuzzType.BUZZ) {
                        this.wait();
                    } else {
                        printBuzz.run();
                        setFlag(i + 1);
                        i++;
                        this.notifyAll();
                    }
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (i < n +1) {
                synchronized (this) {
                    if (flag != FizzBuzzType.FIZZ_BUZZ) {
                        this.wait();
                    } else {
                        printFizzBuzz.run();
                        setFlag(i + 1);
                        i++;
                        this.notifyAll();
                    }
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (i < n +1) {
                synchronized (this) {
                    if (flag != FizzBuzzType.NUM) {
                        this.wait();
                    } else {
                        printNumber.accept(i);
                        setFlag(i + 1);
                        i++;
                        this.notifyAll();
                    }
                }
            }
        }

        private void setFlag(int i) {
            if (i % 3 == 0 && i % 5 == 0) {
                flag = FizzBuzzType.FIZZ_BUZZ;
                return;
            }
            if (i % 3 == 0) {
                flag = FizzBuzzType.FIZZ;
                return;
            }
            if (i % 5 == 0) {
                flag = FizzBuzzType.BUZZ;
                return;
            }
            flag = FizzBuzzType.NUM;
        }

        private enum FizzBuzzType {
            FIZZ, BUZZ, FIZZ_BUZZ, NUM;
        }
    }

    private static class MyThread extends Thread {
        private FizzBuzz fizzBuzz;
        private Runnable runnable;
        private IntConsumer intConsumer;
        int num;

        MyThread(FizzBuzz buzz, Runnable runnable, int num) {
            this.fizzBuzz = buzz;
            this.runnable = runnable;
            this.intConsumer = null;
            this.num = num;
        }

        MyThread(FizzBuzz buzz, IntConsumer consumer, int num) {
            this.fizzBuzz = buzz;
            this.runnable = null;
            this.intConsumer = consumer;
            this.num = num;
        }

        public void run() {
            try {
                switch (num) {
                    case 1:
                        fizzBuzz.fizz(runnable);
                        break;
                    case 2:
                        fizzBuzz.buzz(runnable);
                        break;
                    case 3:
                        fizzBuzz.fizzbuzz(runnable);
                        break;
                    case 4:
                        fizzBuzz.number(intConsumer);
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        int n = sc.nextInt();

        FizzBuzz fizzBuzz = new FizzBuzz(n);
        new MyThread(fizzBuzz, () -> System.out.print("fizz"), 1).start();
        new MyThread(fizzBuzz, () -> System.out.print("buzz"), 2).start();
        new MyThread(fizzBuzz, () -> System.out.print("fizzbuzz"), 3).start();
        new MyThread(fizzBuzz, (i) -> System.out.print(i), 4).start();
    }
}
