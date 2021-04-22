package async.parallel;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ErrorsInStreams {
    public static void main(String[] args) {
        var x = List.of("1", "2", "3", "4");
        var filePath = Paths.get("test.txt");

        // This is how you have to handle a checked exception in
        x.forEach(s -> {
            try {
                Files.writeString(filePath, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static String trySomething(String string) {
        throw new RuntimeException("Some fake exception");
    }
}
