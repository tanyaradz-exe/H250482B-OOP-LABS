// The public contract callers depend on. The internal unit is hidden behind it.
public interface TemperatureReading {
    double getCelsius();
    double getFahrenheit();
    double getKelvin();
}
