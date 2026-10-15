import java.util.ArrayList;
import java.util.List;

public class ImmutablePersonDemo {
    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Nyasha", 20, hobbies);

        hobbies.add("gaming");                    // caller changes THEIR list
        System.out.println(p);                    // p is unaffected

        try {
            p.getHobbies().add("hacking");        // outsiders cannot change it either
        } catch (UnsupportedOperationException e) {
            System.out.println("getHobbies() list is read-only");
        }

        ImmutablePerson older = p.withAge(21);
        System.out.println(p);
        System.out.println(older);
    }
}
