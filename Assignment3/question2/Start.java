package question2;

public class Start {
public static void main(String[] args) {
		Circle c = new Circle(1, 2, 5), c1 = new Circle(2, 5, 9);
		Rectangle r = new Rectangle(-4, 7, 10, 10), r1 = new Rectangle(-1, 3, 8, 4);
		Square s = new Square(-4, 7, 10), s1 = new Square(-4, 7, 8);
		System.out.println( c + " perimeter: " + c.perimeter());
		System.out.println(c1 + " perimeter: " + c1.perimeter());
		System.out.println( r + " perimeter: " + r.perimeter());
		System.out.println( r1 + " perimeter: " + r1.perimeter());
		System.out.println( s + " perimeter: " + s.perimeter());
		System.out.println( s1 + " perimeter: "+ s1.perimeter());
		// Test isInscribed method
		System.out.print(c.toString() + " is inscribed in " + c1.toString() + ": ");
		System.out.println(c.isInscribed(c1));
		
		System.out.print(c.toString() + " is inscribed in " + r.toString()+ ": ");
		System.out.println(c.isInscribed(r));
		
		System.out.print(c.toString() + " is inscribed in " + r1.toString()+ ": ");
		System.out.println(c.isInscribed(r1));
		
		System.out.print(c.toString() + " is inscribed in " + s.toString()+ ": ");
		System.out.println(c.isInscribed(s));
		
		System.out.print(c.toString() + " is inscribed in " + s1.toString()+ ": ");
		System.out.println(c.isInscribed(s1));	
	}
}