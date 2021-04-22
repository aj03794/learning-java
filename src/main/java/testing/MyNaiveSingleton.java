package testing;

public class MyNaiveSingleton {

    private static MyNaiveSingleton instance;

    public MyNaiveSingleton() {
        System.out.println("Calling constructor");
    }

    public static MyNaiveSingleton getInstance() {
        if (instance == null) {
            instance = new MyNaiveSingleton();
            return instance;
        }
        return instance;
    }

    public int doSomething() {
        System.out.println("Some long running process...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    // This idea is from Michael Feather's "Working Effectively with Legacy code"
    // to make this work, we also have to make the constructor public
    public static void setTestingInstance(MyNaiveSingleton newInstance) {
        instance = newInstance;
    }

}

