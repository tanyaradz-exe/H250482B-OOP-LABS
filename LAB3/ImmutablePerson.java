import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1. final class: nobody can subclass it and add mutable behaviour.
public final class ImmutablePerson {

    // 2. private final fields: cannot be reassigned after construction, nor touched from outside.
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.name = name;
        this.age = age;
        // 3. defensive copy IN: the caller's list can change later without affecting us.
        this.hobbies = Collections.unmodifiableList(new ArrayList<>(hobbies));
    }

    // 4. getters only, NO setters.
    public String getName() { return name; }
    public int getAge()     { return age; }

    // 5. hobbies is already unmodifiable, so returning it is safe (defensive copy OUT).
    public List<String> getHobbies() { return hobbies; }

    // 6. "Changing" a value returns a NEW object ("wither" style).
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(name, newAge, hobbies);
    }

    @Override
    public String toString() { return name + " (" + age + "), hobbies=" + hobbies; }
}
