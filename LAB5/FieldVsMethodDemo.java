class Parent {
    String label = "field of Parent";
    String who()  { return "Parent.who()"; }
    static String kind() { return "Parent.kind() (static)"; }
}

class Child extends Parent {
    String label = "field of Child";           // HIDES the parent's field, does not override it
    @Override String who()  { return "Child.who()"; }
    static String kind() { return "Child.kind() (static)"; }
}

public class FieldVsMethodDemo {
    public static void main(String[] args) {
        Parent p = new Child();                // superclass reference, subclass object

        System.out.println("Method: " + p.who());     // Child.who()   (decided at RUN time)
        System.out.println("Field : " + p.label);     // Parent's field (decided at COMPILE time)
        System.out.println("Static: " + p.kind());    // Parent's static (compile time)
        System.out.println("Field via cast: " + ((Child) p).label);
    }
}

/*
WHY THE OUTPUT DIFFERS
  Methods are dispatched dynamically: the JVM looks at the real object (a Child) and runs
  Child.who(). Fields (and static methods) are resolved statically from the DECLARED type
  of the reference (Parent), so p.label is Parent's field even though the object is a Child.
  Lesson: only instance methods are polymorphic. Avoid hiding fields.
*/
