package executor_service_and_thread_pools;

import java.util.concurrent.*;

public class ScheduledPoolWithFixedDelay {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(50);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 2000 here is the initial time to wait before starting this
        // then it executes every 1/2 second
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("Hello from scheduleWithFixedDelay: " + Thread.currentThread().getId());
        }, 2000, 500, TimeUnit.MILLISECONDS);
    }
}
