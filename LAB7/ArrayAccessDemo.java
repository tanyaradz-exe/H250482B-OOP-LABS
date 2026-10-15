public class ArrayAccessDemo {

    static int safeGet(int[] data, int index) {
        try {
            return data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index " + index + " is invalid for length " + data.length
                    + " (" + e.getMessage() + ")");
            return -1;     // sentinel meaning "not found"
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30};
        System.out.println("numbers[1] = " + safeGet(numbers, 1));
        System.out.println("numbers[5] = " + safeGet(numbers, 5));
        System.out.println("numbers[-1] = " + safeGet(numbers, -1));
    }
}
