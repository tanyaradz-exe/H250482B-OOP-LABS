public class AgeValidator {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException(age);
        }
        System.out.println("Age " + age + " is valid.");
    }

    public static void main(String[] args) {
        int[] tests = {25, 0, 120, -1, 121};
        for (int age : tests) {
            try {
                validateAge(age);
            } catch (InvalidAgeException e) {
                System.out.println("Caught: " + e.getMessage());
            }
        }
    }
}
