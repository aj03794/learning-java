package streams;

import java.util.ArrayList;
import java.util.List;

// Collection = mutable reduction
// Reduction operation this is reduced into a mutable collection container
public class CollectingStream {

    public static void main(String[] args) {

        var products = ExampleData.getProducts();

        var res = products.parallelStream().reduce(
                new ArrayList<String>(),
                (list, product) -> {
                    System.out.println(list + " " + product);
                    var newList = new ArrayList<>(list);
                    newList.add(product.getName());
                    return newList;
                },
                (list1, list2) -> {
                    System.out.println("---------");
                    System.out.println(list1 + " " + list2);
                    var newList = new ArrayList<>(list1);
                    newList.addAll(list2);
                    return newList;
                }
        );

        System.out.println("=================");

        var res2= products.stream().collect(
                ArrayList::new,
                (list, product) -> list.add(product.getName()),
                ArrayList::addAll
        );

        System.out.println(res2);

    }

}
