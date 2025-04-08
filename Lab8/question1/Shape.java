package question1;

// The Shape class must be abstract because the area and resize
// methods are abstract.
public abstract class Shape {
	private double x;
	private double y;
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void move(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}
	public abstract double area();
	public abstract void resize(double newSize);
	// The testShape method is empty because we cannot create objects
	// from the Shape class, since it is abstract.
	public static void testShape() {}
}