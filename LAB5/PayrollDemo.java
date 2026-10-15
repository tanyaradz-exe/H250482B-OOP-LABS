public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Kudzai", 2000),
            new Manager("Nyasha", 3500, 800),
            new Developer("Tatenda", 3000, 10),
            new Intern("Chipo", 400)               // added later: loop below unchanged
        };

        double total = 0;
        for (Employee e : staff) {                 // works for any current or future subclass
            e.describe();
            total += e.calculatePay();
        }
        System.out.printf("TOTAL PAYROLL: %.2f%n", total);
    }
}
