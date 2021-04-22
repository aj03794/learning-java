package generics;

import java.util.HashSet;
import java.util.List;

public class BoundedGenerics {

    public <T extends Number> List<T> fromArrayToList(T[] t) {
        return List.of(t);
    }

    public <T, S extends Number> void multipleTypeParams(T t, S s) {
        System.out.println("T: " + t + " S : " + s);
    }

    public <T extends Number> void doSomethingWith(HashSet<T> t) {
        System.out.println("Doing something with: " + t);
    }


    public <T extends Number & Comparable<T>> void extendingNumberAndComparable(T t) {
        System.out.println(t);
    }


}
