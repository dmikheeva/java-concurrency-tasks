package concurrent.forkJoinPool;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class FindMaxTask extends RecursiveTask<Integer> {
    private int[] array;
    private int start, end;

    FindMaxTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 100) {
            int max = -1;
            for (int i = start; i < end; i++) {
                max = Integer.max(max, array[i]);
            }
            return max;
        } else {
            int mid = start + (end - start) / 2;
            System.out.printf("Splitting array into two: (%d %d) and (%d %d)%n", start, mid, mid + 1, end);

            ForkJoinTask<Integer> left = new FindMaxTask(array, start, mid).fork();
            ForkJoinTask<Integer> right = new FindMaxTask(array, mid + 1, end).fork();
            return Integer.max(left.join(), right.join());
            //the same with just invoke
            //Integer left = new FindMaxTask(array, start, mid).invoke();
            //Integer right = new FindMaxTask(array, mid + 1, end).invoke();
            //return Integer.max(left, right);
        }
    }
}
