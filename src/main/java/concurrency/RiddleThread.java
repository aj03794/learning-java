package concurrency;

public class RiddleThread extends Thread {

    public void run() {
        System.out.println("I'm the task of CustomThread");
        System.out.println("Thread ID in task is: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        var riddleThread = new RiddleThread();
        riddleThread.run();
        System.out.println("Thread ID in main is: " + Thread.currentThread().getId());
    }
}
