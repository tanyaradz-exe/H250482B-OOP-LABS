class Vehicle {
    Vehicle() {
        System.out.println("Vehicle constructor called");
    }
}

class Car extends Vehicle {
    Car() {
        System.out.println("Car constructor called");
    }
}

class ElectricCar extends Car {
    ElectricCar() {
        System.out.println("ElectricCar constructor called");
    }
}

public class VehicleTest {
    public static void main(String[] args) {
        new ElectricCar();
    }
}