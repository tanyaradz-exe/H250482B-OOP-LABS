public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<String> words = new MyStack<>();
        words.push("one");
        words.push("two");
        words.push("three");
        System.out.println("peek = " + words.peek() + ", size = " + words.size());
        while (!words.isEmpty()) System.out.println("pop  = " + words.pop());

        MyStack<Integer> ints = new MyStack<>();
        ints.push(10);
        ints.push(20);
        int top = ints.pop();                      // no cast needed: compiler knows T = Integer
        System.out.println("top = " + top);
        // ints.push("text");                       // COMPILE ERROR: type safety

        try {
            ints.pop(); ints.pop();
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
