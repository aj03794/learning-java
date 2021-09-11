package executor_service_and_thread_pools;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class SingleExecuteExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println("1: " + Math.random() + " Thread Id: " + Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("2: " + Math.random() + " Thread Id: " + Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("3: " + Math.random() + " Thread Id: " + Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("4: " + Math.random() + " Thread Id: " + Thread.currentThread().getId()));

        var list = new ArrayList<Callable<Integer>>();
        list.add(() -> 1);
        list.add(() -> 2);
        list.add(() -> 3);

        Thread.sleep(100);
        // Invokes ANY of the tasks in the list
        // In case of singleThreadExecutor, will always call first task in callables list
        // Cancels remaining tasks once first task it picks up finishes
        var res = executorService.invokeAny(list);
        var res2 = executorService.invokeAll(list);
        System.out.println("Result: " + res);
        System.out.println("Result2: " + res2);
        // Have to do this otherwise program won't shutdown
        executorService.shutdown();
    }
}
