import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter the first integer: ");
            int a = in.nextInt();
            System.out.print("Enter the second integer: ");
            int b = in.nextInt();
            System.out.println(a + " / " + b + " = " + (a / b));   // ArithmeticException if b == 0
        } catch (InputMismatchException e) {
            System.out.println("Error: that was not a valid whole number.");
        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");
        } finally {
            in.close();
        }
    }
}
