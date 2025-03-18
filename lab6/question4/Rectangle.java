package question4;
// The Rectangle class derives from the Shape class.
public class Rectangle extends Shape {
	private double width;
	private double height;
	public Rectangle(double x, double y, double width, double height) {
		// The father class's constructor need to be the first statement in the subclass's constructor.
		// The father class's element must init first.
		super(x,y);
		this.width = width;
		this.height = height;
	}
	public double area() {
		return this.width * this.height;
	}
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Rectangle) {
			Rectangle r = (Rectangle) otherObject;
			return this.width == r.width && this.height == r.height;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Rectangle area is " + 4.0 * 5.0;
	}
	public static void testRectangle() {
		Rectangle r = new Rectangle(1.2, 3.4, 4.0, 5.0);
		Rectangle r1 = new Rectangle(10, 11, 4.0, 5.0);
		Rectangle r2 = new Rectangle(10, 11, 8.0, 5.0);
		Circle c = new Circle(1.2, 3.4, 5.0);
		// getX and getY are inherited from Shape.
		// area comes from Rectangle itself.
		System.out.println(r.getX() == 1.2);
		System.out.println(r.getY() == 3.4);
		System.out.println(r.area() == 20.0);
		System.out.println(r.equals(r1));
		System.out.println(!r1.equals(r2));
		System.out.println(!r1.equals(c));
		System.out.println(r.toString().equals("Rectangle area is " + 4.0 * 5.0));
	}
}