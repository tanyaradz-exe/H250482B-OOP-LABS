public class TemperatureDemo {
    public static void main(String[] args) {
        Temperature boiling = Temperature.fromCelsius(100);
        Temperature body = Temperature.fromFahrenheit(98.6);
        System.out.println("Boiling water: " + boiling);
        System.out.println("Body temperature: " + body);
        // new Temperature(5);   // COMPILE ERROR: constructor is private
    }
}
