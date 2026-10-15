public class RectangleDemo {
    public static void main(String[] args) {
        // Part 1: array of 5 rectangles, find the largest area
        Rectangle[] rects = {
            new Rectangle(2, 3),
            new Rectangle(5, 4),
            new Rectangle(1, 10),
            new Rectangle(6, 6),
            new Rectangle(3, 7)
        };

        Rectangle largest = rects[0];
        for (Rectangle r : rects) {
            if (r.area() > largest.area()) {
                largest = r;
            }
        }
        System.out.println("Largest: " + largest);

        // Part 2: aliasing. PREDICTION: prints 99.0, because r2 and r1 are two
        // references to ONE object, so changing it through r2 is visible through r1.
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;       // copies the reference (address), not the object
        r2.width = 99;
        System.out.println("r1.width after r2.width = 99: " + r1.width);
        System.out.println("r1 == r2 ? " + (r1 == r2));
    }
}
