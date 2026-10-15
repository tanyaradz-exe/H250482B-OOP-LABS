import java.time.Year;

public class Car {
    String brand;
    int year;
    double mileage;

    Car(String brand, int year, double mileage) {
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
    }

    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    // Antique = more than 25 years old
    boolean isAntique() {
        return Year.now().getValue() - year > 25;
    }
}
