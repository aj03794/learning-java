package executor_service_and_thread_pools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleSubmitExample {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static Future<Double> getRandom() {
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return Math.random();
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var doubleFuture = getRandom();
        while (!doubleFuture.isDone()) {
            if(doubleFuture.isCancelled()) {
                System.out.println("Your future was cancelled");
                break;
            }
            System.out.println("Sleeping");
            Thread.sleep(100);
        }
        System.out.println("Double future result: " + doubleFuture.get());

        executorService.shutdown();
    }
}
