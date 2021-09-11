package streams;

import java.util.List;

public class ExampleData {
    public static List<Product> getProducts() {
        var product1 = new Product("name1", 1);
        var product2 = new Product("name2", 2);
        var product3 = new Product("name3", 3);
        var product4 = new Product("name4", 4);

        return List.of(product1, product2, product3, product4);
    }
}
