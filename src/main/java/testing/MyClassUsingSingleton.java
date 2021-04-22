package testing;

public class MyClassUsingSingleton {

    public int doSomething() {
        MyNaiveSingleton myNaiveSingleton = MyNaiveSingleton.getInstance();
        return myNaiveSingleton.doSomething() * 2;
    }
}
