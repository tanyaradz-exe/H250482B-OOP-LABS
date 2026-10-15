public class Rectangle {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double area()      { return width * height; }
    double perimeter() { return 2 * (width + height); }

    @Override
    public String toString() {
        return "Rectangle[" + width + " x " + height + ", area=" + area() + "]";
    }
}
