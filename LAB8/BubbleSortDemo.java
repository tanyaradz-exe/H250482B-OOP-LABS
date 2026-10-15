import java.util.Arrays;

public class BubbleSortDemo {

    // T must be comparable to itself, so we can call compareTo
    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;           // already sorted: stop early
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {42, 7, 19, 3, 88, 1};
        String[] words = {"mango", "apple", "pear", "banana"};

        bubbleSort(nums);
        bubbleSort(words);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(words));

        // int[] raw = {3, 1, 2};
        // bubbleSort(raw);      // COMPILE ERROR
    }
}

/*
WHY int[] WILL NOT WORK
  Generics work only with REFERENCE types, because type arguments are erased to Object at run
  time. T[] would have to be a Comparable[]/Object[] array of references. int is a primitive,
  and int[] is not a T[] for any T (an int[] is not an Object[] and int is not a Comparable).
  Use the wrapper type: Integer[] (boxing each value), or write a separate non-generic sort for int[].
*/
