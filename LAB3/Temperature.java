// Stores KELVIN internally.
public class Temperature implements TemperatureReading {
    private static final double ABSOLUTE_ZERO_C = -273.15;
    private final double kelvin;

    private Temperature(double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature is below absolute zero (0 K = -273.15 C)");
        }
        this.kelvin = kelvin;
    }

    public static Temperature ofCelsius(double c)    { return new Temperature(c - ABSOLUTE_ZERO_C); }
    public static Temperature ofFahrenheit(double f) { return ofCelsius((f - 32) * 5 / 9); }
    public static Temperature ofKelvin(double k)     { return new Temperature(k); }

    @Override public double getKelvin()     { return kelvin; }
    @Override public double getCelsius()    { return kelvin + ABSOLUTE_ZERO_C; }
    @Override public double getFahrenheit() { return getCelsius() * 9 / 5 + 32; }
}
