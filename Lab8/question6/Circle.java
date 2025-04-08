package question6;

// The Circle class derives from the Shape class.
public class Circle extends Shape {
	private double radius;
	public Circle(double x, double y, double radius) {
		super(x, y); // Call the constructor of the superclass (Shape).
		this.radius = radius;
	}
	@Override
	public double area() {
		return Math.PI * radius * radius; // Area of the circle.
	}
	@Override
	public void resize(double newRadius) {
		this.radius = newRadius; // Resize the circle by changing the radius.
	}
	// The Circle class is not abstract (it has code for all methods) so
	// we can test it.
	public static void testCircle() {
		Circle c = new Circle(1.2, 3.4, 4.0);
		// getX, getY, and move are inherited from Shape.
		// area and resize come from Circle itself.
		System.out.println(c.getX() == 1.2);
		System.out.println(c.getY() == 3.4);
		System.out.println(c.area() == Math.PI * 16.0);
		// Move the circle. The area does not change.
		c.move(7.8, 9.0);
		System.out.println(c.getX() == 9.0);
		System.out.println(c.getY() == 12.4);
		System.out.println(c.area() == Math.PI * 16.0);
		// Resize the circle. The area changes but not the position.
		c.resize(8.0);
		System.out.println(c.getX() == 9.0);
		System.out.println(c.getY() == 12.4);
		System.out.println(c.area() == Math.PI * 64.0);
	}
}