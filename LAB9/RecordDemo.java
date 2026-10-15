record Student(String name, int age) { }

public class RecordDemo {
    public static void main(String[] args) {
        Student a = new Student("Tariro", 20);
        Student b = new Student("Tariro", 20);
        Student c = new Student("Farai", 21);

        System.out.println(a);                                  // auto toString
        System.out.println("a.equals(b): " + a.equals(b));      // auto equals: compares components
        System.out.println("a == b     : " + (a == b));         // different objects
        System.out.println("a.equals(c): " + a.equals(c));
        System.out.println("same hash  : " + (a.hashCode() == b.hashCode()));
        System.out.println("name() = " + a.name() + ", age() = " + a.age());   // accessors, not getName()
    }
}
