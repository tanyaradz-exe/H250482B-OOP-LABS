public class Circle {
    private static int count = 0;      // one copy shared by all circles
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        count++;
    }

    public Circle() {
        this(1.0);                     // default radius 1.0 (constructor chaining)
    }

    public static int getCount() { return count; }
    public double getRadius()    { return radius; }
    public double area()         { return Math.PI * radius * radius; }
}
