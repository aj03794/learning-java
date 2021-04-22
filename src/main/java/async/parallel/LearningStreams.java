package async.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LearningStreams {

    public static void main(String[] args) {
        var streamEmpty = Stream.empty();
        streamOfCollection();
        streamOfArray();
        generateStream();
        iterateStream();
        primitiveStream();
        testingOutSkip();
        streamReduce();
    }

    public static void streamOfCollection() {
        var StreamFromCollection = Arrays.asList("a", "b", "c");
    }

    public static void streamOfArray() {
        var streamFromArray = Stream.of("a", "b", "c");

        var arr = new String[]{"a", "b", "c"};
        // Only prints b and c
        var partialArrayStream = Arrays.stream(arr, 1, 3);
        partialArrayStream.forEach(System.out::println);
    }

    public static void generateStream() {
        // This creates an endless stream
//        var generatedStream = Stream.generate(() -> "element");
        var generatedStream = Stream.generate(() -> "element").limit(10);
        generatedStream.forEach(System.out::println);

        // Can also add a type before builder() -> <String>
        var builtStream = Stream.builder().add("1").add(2).add(true).build();
        builtStream.forEach(System.out::println);
    }

    public static void iterateStream() {
        // UnaryOperator is simply a function that takes a type and returns that same type (usually it performs some calculation)
        // example here is n -> n + 2
        var streamIterated = Stream.iterate(40, n -> n + 2).limit(4);
        streamIterated.forEach(System.out::println);
    }

    public static void primitiveStream() {
        // Start inclusive, end exclusive
        IntStream.range(1, 3).forEach(System.out::println);
        // Start and end is inclusive
        LongStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    public static void testingOutSkip() {
        System.out.println("Testing out skip");
        var list = List.of("abc", "cde", "efg");
        list.stream().skip(1).forEach(System.out::println);
        list.stream().skip(1).map(element -> element.substring(1, 2)).forEach(System.out::println);
    }

    public static void streamReduce() {
        System.out.println("Stream Reduce");
        var res1 = IntStream.rangeClosed(1, 6).reduce((a, b) -> {
            System.out.println("a: " + a + " b: " + b);
            return 2 * a + 2 * b;
        });
        System.out.println(res1.toString());

        var res2 = IntStream.rangeClosed(1, 3).reduce(10, (a, b) -> {
            System.out.println("a: " + a + " b: " + b);
            return 2 * a + 2 * b;
        });
        System.out.println(res2);

//        var res3 =

    }

}