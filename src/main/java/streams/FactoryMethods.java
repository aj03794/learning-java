package streams;

import java.math.BigInteger;
import java.util.UUID;
import java.util.stream.Stream;

public class FactoryMethods {
    public static void main(String[] args) {
        var uuids = Stream.generate(UUID::randomUUID);
        uuids.limit(10).forEach(System.out::println);

        var powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));

        // These 3 arguments are like that of a for loop
        Stream.iterate('A', letter -> letter <= 'Z', letter -> (char) (letter + 1)).forEach(System.out::println);

        var builder = Stream.builder();
        builder.add("one");
        builder.add("two");
        builder.add("three");
        var stream = builder.build();
        stream.forEach(System.out::println);
    }
}
