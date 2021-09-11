package threads_and_locks;

public class Count {
    public static int counter = 0;

    public static void incrementCounter() {
        int current = counter;
        System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
        counter = current + 1;
        System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(Count::incrementCounter);
            t.start();
        }
    }
}
