package ClassesAndInterfaces;

public class Main {
    public static void main(String[] args) {
        var classWithNestedClass = new ClassWithNestedClass();
        classWithNestedClass.run();
        classWithNestedClass.runLocalClass();
        classWithNestedClass.runAnonymousClass();
    }
}
