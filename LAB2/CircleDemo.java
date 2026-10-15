public class CircleDemo {
    public static void main(String[] args) {
        System.out.println("Circles at start: " + Circle.getCount());
        Circle a = new Circle(2.5);
        Circle b = new Circle();
        Circle c = new Circle(4);
        System.out.println("a radius = " + a.getRadius());
        System.out.println("b radius = " + b.getRadius() + " (default)");
        System.out.println("c area   = " + String.format("%.2f", c.area()));
        System.out.println("Circles created: " + Circle.getCount());
    }
}
