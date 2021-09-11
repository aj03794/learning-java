package concurrency;

import java.util.concurrent.Callable;

public class CallThread implements Callable<Object> {

    @Override
    public Object call() {
        System.out.println("In callable thread");
        return new Object();
    }

}
