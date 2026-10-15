public class LinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");

        for (String s : list) {                 // works because of Iterable<T>
            System.out.println(s);
        }
        System.out.println("size = " + list.size());
    }
}
