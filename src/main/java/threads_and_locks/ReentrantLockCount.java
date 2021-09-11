package threads_and_locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCount {
    public static int counter = 0;
    public static Lock lock = new ReentrantLock();

    // Good practice to run this with try/finally
    // because finally always gets executed
    public static void incrementCounter() {
        try {
            lock.lock();
            int current = counter;
            System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
            counter = current + 1;
            System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
        } finally {
            lock.unlock();
        }
    }

    public static void incrementCounterWithTryLock() throws InterruptedException {
//        if (lock.tryLock()) {
    if (lock.tryLock(50, TimeUnit.MILLISECONDS)) {
        try {
            int current = counter;
            System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
            counter = current + 1;
            System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Can't get lock");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    ReentrantLockCount.incrementCounterWithTryLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
}
