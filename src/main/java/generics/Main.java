package generics;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        var boundedGenerics = new BoundedGenerics();
        // Integer extends number
        var arr = new Integer[]{ 1 };
        System.out.println(boundedGenerics.fromArrayToList(arr));

        var arr2 = new Double[]{ 1.0, 2.0, 3.0 };
        System.out.println(boundedGenerics.fromArrayToList(arr2));

        var linkedHashSet = new HashSet<Integer>();
        linkedHashSet.add(1);
        boundedGenerics.doSomethingWith(linkedHashSet);

        // Integer implements Comparable<Integer> and extends from Number
        boundedGenerics.extendingNumberAndComparable(1);

        boundedGenerics.multipleTypeParams(1.0, 1);
    }
}
