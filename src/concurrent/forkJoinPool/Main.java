package concurrent.forkJoinPool;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int n = 1000;
        int[] array = new int[n];
        //init array
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool();
        Integer max = pool.invoke(new FindMaxTask(array, 0, array.length));
        System.out.println("result:" + max);
    }
}
