package threads_and_locks;

public class SynchronizedCount {
    public static int counter = 0;
    // Object lock
    // This lock needs to be the same instance every time it's used
    public static Object lock = new Object();

    // SYNCHRONIZED METHOD
    // Prevents all the threads from accessing this method at the same time
    // If thread gets here and doesn't have access, it's going to wait in a stack
    // Access to block works in last in first out order
    // Last thread to try to get access will be the first to get access after
    // current thread is done with it
    public synchronized static void incrementCounter() {
        int current = counter;
        System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
        counter = current + 1;
        System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
    }

    public synchronized static void incrementCounterWithObjectLock() {
        // Synchronized "this" is common when the method is not static
        // full instance gets locked in this scenario
        synchronized (lock) {
            int current = counter;
            System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
            counter = current + 1;
            System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            Thread t = new Thread(SynchronizedCount::incrementCounter);
            Thread t2 = new Thread(SynchronizedCount::incrementCounterWithObjectLock);
//            t.start();
            t2.start();
        }
    }
}
