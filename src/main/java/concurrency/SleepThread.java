package concurrency;

public class SleepThread implements Runnable {
    @Override
    public void run() {
        System.out.println("In RunThread run method");

        for (int i = 0; i < 10; i++) {
            System.out.println("Current thread id: " + Thread.currentThread().getId() + " i: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
//                Thread.currentThread().interrupt()
                break;
            }
        }
    }

    public static void main(String[] args) {
        var t = new Thread(new SleepThread());
        var t2 = new Thread(new SleepThread());
        t.start();
        try {
            // Wait for forever for t to end before starting t2
//            t.join();
            t.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        // Tell thread to stop
//        t.interrupt();
    }
}
