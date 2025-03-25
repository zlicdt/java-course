package question4;
// The Circle class derives from the Shape class.
public class Circle extends Shape {
	private double radius;
	public Circle(double x, double y, double radius) {
		// The father class's constructor need to be the first statement in the subclass's constructor.
		// The father class's element must init first.
		super(x,y);
		this.radius = radius;
	}
	public double area() {
		return Math.PI * this.radius * this.radius;
	}
	@Override
	public String toString() {
		return "Circle area is " + Math.PI * 16.0;
	}
	public static void testCircle() {
		Circle c = new Circle(1.2, 3.4, 4.0);
		// getX and getY are inherited from Shape.
		// area comes from Circle itself.
		System.out.println(c.getX() == 1.2);
		System.out.println(c.getY() == 3.4);
		System.out.println(c.area() == Math.PI * 16.0);
		System.out.println(c.toString().equals("Circle area is " + Math.PI * 16.0));
	}
}

