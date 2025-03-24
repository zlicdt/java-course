package question2;

public class Circle extends ClosedFigure {
    // Instance variables
    private double x, y, radius;
    public Circle(double x, double y, double radius) {
        super(1);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    // Getters
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
    public boolean isInscribed(Object object) {
        if (object instanceof Rectangle) {
            Rectangle r = (Rectangle) object;
            return (this.x == r.getX() + r.getWidth() / 2 && this.y == r.getY() - r.getHeight() / 2 && this.radius == r.getWidth() / 2);
        }
        return false;
    }
    // Override toString method
    @Override
    public String toString() {
        return "Circle(" + x + "," + y + "," + radius + ")";
    }
}

