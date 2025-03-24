package question2;

public class Rectangle extends ClosedFigure {
    private double x, y, width, height;
    public Rectangle(double x, double y, double width, double height) {
        super(4);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    // The x, y, width, and height are the coordinates of the top-left corner of the rectangle
    // They are private, needs getters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
    @Override
    public String toString() {
        return "Rectangle(" + x + "," + y + "," + width + "," + height + ")";
    }
}