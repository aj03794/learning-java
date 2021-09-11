package streams;

import java.math.BigDecimal;
import java.util.List;

// For immutable reduction
public class ReducingStream {

    public static void main(String[] args) {
        var products = List.of(1,2,3,4);

        var res = products.stream()
                .map(product -> product * 2)
                .reduce((result, product) -> result += product);

        res.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("There are no products")
        );

        var res2 = products.stream()
                .map(product -> product * 2)
                .reduce(0, (result, product) -> result += product);
        System.out.println(res2);

        // Using this reduce with the 3rd element is useful
        // when doing parallel streams
        var res3 = products.stream()
                .reduce(0, (result, product) -> result += product, (result, product) -> product * result);
        System.out.println(res3);
    }
}
