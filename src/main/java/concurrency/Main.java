package concurrency;

public class Main {
    public static void main(String[] args) {
        // Constructor takes a runnable
        var t = new Thread(() -> System.out.println("Runnable: " + Thread.currentThread().getId()));
        System.out.println("Main: " + Thread.currentThread().getId());
        t.start();

        System.out.println("--------------");
        var customThread = new CustomThread();
        customThread.start();
    }
}
