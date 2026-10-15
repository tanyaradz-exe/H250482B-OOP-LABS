class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class StudentHierarchy extends Person {
    String course;

    StudentHierarchy(String name, int age, String course) {
        super(name, age);
        this.course = course;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Course: " + course);
    }
}

public class PersonHierarchy {
    public static void main(String[] args) {
        StudentHierarchy s = new StudentHierarchy("Nigel", 22, "Computer Science");
        s.display();
    }
}