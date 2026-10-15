public class TemperatureDemo {

    // Caller code: uses ONLY the public methods, never the internal unit.
    static void report(TemperatureReading t) {
        System.out.printf("%.2f C | %.2f F | %.2f K%n",
                t.getCelsius(), t.getFahrenheit(), t.getKelvin());
    }

    public static void main(String[] args) {
        report(Temperature.ofCelsius(25));                 // kelvin inside
        report(new TemperatureCelsiusBased(25));           // celsius inside: identical output
        report(Temperature.ofFahrenheit(212));

        try {
            Temperature.ofCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Same caller code works with both internal designs, "
                + "so changing the stored unit cannot break callers.");
    }
}
