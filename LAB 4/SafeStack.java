import java.util.ArrayList;
import java.util.List;

public class SafeStack<T> {
    private final List<T> list = new ArrayList<>(); 

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        SafeStack<String> stack = new SafeStack<>();
        stack.push("First item");
        System.out.println(stack.pop());
    }
}