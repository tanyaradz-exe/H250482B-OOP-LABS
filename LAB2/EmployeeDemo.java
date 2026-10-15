public class EmployeeDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee("Tendai", "IT", 2500);
        Employee e2 = new Employee("Rudo", "HR");
        Employee e3 = new Employee("Chipo");
        Employee e4 = new Employee(e1);            // copy constructor

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);
        System.out.println("e1 == e4 ? " + (e1 == e4) + " (independent object, new id)");
    }
}
