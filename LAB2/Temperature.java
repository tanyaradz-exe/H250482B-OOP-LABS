public class Temperature {
    private final double celsius;

    private Temperature(double celsius) {      // private: callers must use the factories
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double c)    { return new Temperature(c); }
    public static Temperature fromFahrenheit(double f) { return new Temperature((f - 32) * 5 / 9); }

    public double getCelsius()    { return celsius; }
    public double getFahrenheit() { return celsius * 9 / 5 + 32; }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", getCelsius(), getFahrenheit());
    }
}

/*
WHY STATIC FACTORIES CAN BE CLEARER THAN OVERLOADED CONSTRUCTORS
 1. They have names. new Temperature(100) cannot say whether 100 is Celsius or Fahrenheit,
    and two constructors with the same signature (double) are illegal anyway.
    fromCelsius(100) and fromFahrenheit(212) are self-documenting.
 2. They may do conversion/validation before building the object, return a cached
    instance, or return a subclass; a constructor always creates a new object of exactly its class.
 3. They keep the internal representation (Celsius) private and replaceable.
*/
