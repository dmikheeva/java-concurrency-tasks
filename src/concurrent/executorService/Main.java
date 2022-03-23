package concurrent.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static int count = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //execute no result returned
        executorService.execute(() ->
                System.out.println("executorService execute")
        );

        //submit return result
        Future<String> f = executorService.submit(() -> "executorService submit!");
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //invokeANy call any of calllables
        System.out.println();
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(() -> "task1");
        callables.add(() -> "task2");
        callables.add(() -> "task3");
        try {
            System.out.println(executorService.invokeAny(callables));
            System.out.println();
            executorService.invokeAll(callables).stream().forEach(t -> {
                try {
                    System.out.println(t.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        System.out.println();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<String> result = scheduledExecutorService.schedule(() -> "scheduled task", 2, TimeUnit.SECONDS);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ScheduledFuture result2 = scheduledExecutorService
                .scheduleAtFixedRate(() -> {
                    System.out.println("scheduled task each 5 sec. Count: " + count);
                    count--;
                }, 1, 2, TimeUnit.SECONDS);
        while (count > 0) {
        }
        ;
        result2.cancel(true);
        scheduledExecutorService.shutdown();
    }



}
