package concurrency;

public class RunThread implements Runnable {
    @Override
    public void run() {
        System.out.println("In RunThread run method");

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
