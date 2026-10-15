// A DIFFERENT internal design (stores CELSIUS) with the same public methods.
public class TemperatureCelsiusBased implements TemperatureReading {
    private final double celsius;

    public TemperatureCelsiusBased(double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Below absolute zero: " + celsius + " C");
        }
        this.celsius = celsius;
    }

    @Override public double getCelsius()    { return celsius; }
    @Override public double getFahrenheit() { return celsius * 9 / 5 + 32; }
    @Override public double getKelvin()     { return celsius + 273.15; }
}
