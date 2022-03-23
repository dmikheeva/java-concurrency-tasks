package concurrent.semaphore;


import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(new Incrementer(semaphore, "A")).start();
        new Thread(new Decrementer(semaphore, "B")).start();
    }
}
