public class StaticBlocksDemo {

    static { System.out.println("static block 1"); }
    { System.out.println("instance block 1"); }

    StaticBlocksDemo() { System.out.println("constructor"); }

    static { System.out.println("static block 2"); }
    { System.out.println("instance block 2"); }

    public static void main(String[] args) {
        System.out.println("main starts");
        new StaticBlocksDemo();
        new StaticBlocksDemo();
    }
}

/*
PREDICTED OUTPUT
  static block 1        <- static blocks run once, in written order, when the class loads
  static block 2           (before main)
  main starts
  instance block 1      <- for EVERY object: instance blocks in written order,
  instance block 2         then the constructor body
  constructor
  instance block 1
  instance block 2
  constructor
*/
