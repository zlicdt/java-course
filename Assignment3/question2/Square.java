package question2;

public class Square extends Rectangle {
	private double x, y, side;
    public Square(double x, double y, double side) {
        super(x, y, side, side);
        this.x = x;
        this.y = y;
        this.side = side;
    }
    // Override perimeter method from CloseFigure class
    @Override
    public double perimeter() {
        return 4 * side;
    }
    // Override toString method
    @Override
    public String toString() {
        return "Square(" + x + "," + y + "," + side + ")";
    }
}
