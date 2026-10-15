import java.util.Arrays;

public class PrintDemo {
    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String x)   { System.out.println("String: " + x); }
    static void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }

    public static void main(String[] args) {
        print(42);                     // print(int)
        print(3.14);                   // print(double)
        print("Hello");                // print(String)
        print(new int[]{1, 2, 3});     // print(int[])
        print('A');                    // char widens to int -> print(int) prints 65
        print(10L);                    // long widens to double -> print(double)
    }
}
