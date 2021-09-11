package concurrency;

public class App {
    public static void main(String[] args) {
        var customThread = new CustomThread();
        customThread.start();

        var thread = new Thread(() -> System.out.println("Hi"));
        thread.start();

        System.out.println("-------------");

        var thread2 = new Thread(new RunThread());
        thread2.start();

    }
}
