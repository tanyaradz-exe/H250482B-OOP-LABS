import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStats {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(14, 3, 27, 8, 21, 5, 40, 12, 9, 33));

        int sum = 0;
        for (int n : nums) sum += n;                       // auto-unboxing Integer -> int

        System.out.println("List   : " + nums);
        System.out.println("Sum    : " + sum);
        System.out.println("Maximum: " + Collections.max(nums));

        List<Integer> reversed = new ArrayList<>(nums);
        Collections.reverse(reversed);
        System.out.println("Reverse: " + reversed);
    }
}
