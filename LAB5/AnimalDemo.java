public class AnimalDemo {
    public static void main(String[] args) {
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };
        for (Animal a : zoo) {
            // Same call, different behaviour: chosen at run time by the object's real class
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }
    }
}
