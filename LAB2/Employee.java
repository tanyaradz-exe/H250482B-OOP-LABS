public class Employee {
    private static int nextId = 1000;          // static counter shared by all employees

    private final int employeeId;
    private String name;
    private String department;
    private double salary;

    // Constructor 1: everything supplied
    public Employee(String name, String department, double salary) {
        this.employeeId = ++nextId;            // auto-increment
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Constructor 2: name and department, salary defaults to 0
    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    // Constructor 3: name only
    public Employee(String name) {
        this(name, "Unassigned");
    }

    // Copy constructor: new id (a different employee record), same details
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public int getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return String.format("Employee #%d %s (%s) salary %.2f", employeeId, name, department, salary);
    }
}
