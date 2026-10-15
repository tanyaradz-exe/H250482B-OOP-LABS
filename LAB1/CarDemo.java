public class CarDemo {
    public static void main(String[] args) {
        Car c1 = new Car("Toyota", 1995, 250000);
        Car c2 = new Car("Honda", 2010, 120000.5);
        Car c3 = new Car("Mazda", 2022, 15000);

        Car[] cars = {c1, c2, c3};
        for (Car c : cars) {
            c.display();
            System.out.println("  Antique? " + c.isAntique());
        }
    }
}
