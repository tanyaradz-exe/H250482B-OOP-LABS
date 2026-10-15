public class DefaultConflictDemo implements Greeter1, Greeter2 {

    // Without this override the class does NOT compile:
    //   "types Greeter1 and Greeter2 are incompatible; class inherits unrelated defaults for hello()"
    @Override
    public void hello() {
        Greeter1.super.hello();      // explicitly pick (or combine) the inherited versions
        Greeter2.super.hello();
        System.out.println("Hello from DefaultConflictDemo itself");
    }

    public static void main(String[] args) {
        new DefaultConflictDemo().hello();
    }
}

/*
THE RULE
 1. A class method always wins over an interface default.
 2. A more specific interface (a sub-interface) wins over its super-interface.
 3. If two unrelated interfaces supply the same default, Java refuses to guess: the class
    MUST override the method and may delegate with InterfaceName.super.method().
*/
