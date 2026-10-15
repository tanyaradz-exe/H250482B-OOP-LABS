public class Person {
    String name;
    Address address;    // a field that refers to another object

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
