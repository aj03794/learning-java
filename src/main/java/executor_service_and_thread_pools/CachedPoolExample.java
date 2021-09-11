package executor_service_and_thread_pools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedPoolExample {
    // We don't get to specify number of threads here
    // Spins up threads based on our system
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static Future<Double> getRandom(int i) {
        return executorService.submit(() -> {
            Thread.sleep((int)Math.random() * 200);
            System.out.println(i + " Thread id: " + Thread.currentThread().getId());
            Thread.sleep(1000);
            return Math.random();
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 100; i++) {
            getRandom(i);
        }
        executorService.shutdown();
    }
}
