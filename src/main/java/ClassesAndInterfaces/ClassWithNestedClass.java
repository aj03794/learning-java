package ClassesAndInterfaces;

// Nested classes can be
// static
// non-static
// local
// anonymous
public class ClassWithNestedClass {

    private static int x = 1;

    // Not accessible in static nested class
    private int y = 2;

    // static classes can define both static and non-static members
    // this could be public or private
    // Effective java says this should be a "private static" class
    public static class StaticNested {

        private void run() {
            System.out.println("Running..." + x);
        }

        private static void runStatic() {
            System.out.println("Static running..." + x);
        }
    }

    protected class InnerClass {
        private void run() {
            System.out.println("Running..." + x + " and " + y);
        }
    }

    public void run() {
        new ClassWithNestedClass.StaticNested().run();
        ClassWithNestedClass.StaticNested.runStatic();
        new ClassWithNestedClass.InnerClass().run();
        new ClassWithNestedClass.InnerClassShadowing().run();
    }

    public void runLocalClass() {
        int z = 3;
        class Local {
            void run() {
                System.out.println("Running local class..." + x);
                System.out.println("Has access to enclosing classes static variables and instance variables " + x + " " + y + " " + z);
            }
        }
        new Local().run();
    }

    public void runAnonymousClass() {
        var simpleAbstractClass = new SimpleAbstractClass() {
            void run() {
                System.out.println("Running anonymous class");
            }
        };
        simpleAbstractClass.run();
    }

    private class InnerClassShadowing {
        int y = 2;

        public void run() {
            System.out.println("Inner class: " + y + ", Shadowed variable: " + ClassWithNestedClass.this.y);
        }

    }

}
