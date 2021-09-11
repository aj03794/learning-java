package executor_service_and_thread_pools;

import java.util.concurrent.*;

public class ScheduledPoolExample {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(50);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Double> res = executorService.schedule(() -> {
            Thread.sleep((int)Math.random() * 200);
            System.out.println("Thread id: " + Thread.currentThread().getId());
            Thread.sleep(1000);
            return Math.random();
        }, 1000, TimeUnit.MILLISECONDS);

        // Remember this will block thread until it is done
        // Throws ExecutionException when the callable throws a runtime execption
        System.out.println(res.get());

        executorService.shutdown();
    }
}
