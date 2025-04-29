package question6;

public class Car {
	
	public static void testCar() {
		// Trying to create a car with the wrong number of doors:
		try {
			Car brokencar = new Car("Broken", 0);
		} catch (BadCarException ex) {
			// We can use the == operator here to compare strings because both strings
			// (the one stored inside the exception object, and the one used directly in
			// the test itself) are constant strings. The string stored inside the
			// exception object and returned as result by the getMessage method isa
			// constant string because it is written directly in the code of the
			// Car constructor above.
			System.out.println(ex.getMessage() == "A car must have at least one door!");
		}
		// Create a good car.
		Car c = null;

		try {
			c = new Car("Biggy", 7);
		} catch (BadCarException ex) {
			System.out.println("BUG! This must never happen!");
		}
		// Prints 7 lines of output, each with the car's name and the fact
		// that the door is closed.
		// This method returns void and does not change the car object,
		// so there is no result or change that we can test directly, we
		// can only look at the printed output and make sure it is correct...
		c.listDoors();
		// When the car is created all the doors are closed (by default).
		System.out.println(c.countOpenDoors() == 0);

		// Change all the closed doors into open doors:
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 7);

		// Prints 7 lines of output, each with the car's name and the fact
		// that the door is open.
		c.listDoors();

		// Change all the open doors into close doors:
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 0);

		// Trying to open a nonexistent door again:
		try {
			c.openOneDoor(8);
		} catch (BadDoorException ex) {
			// We must use the equals method to compare the strings, not the ==
			// operator, because the string stored inside the exception object
			// is constructed dynamically at runtime using the + operator in
			// the openOneDoor method above.
			System.out.println(ex.getMessage().equals("Door 8 does not exist!"));
		}

		// Opening door 3
		try {
			c.openOneDoor(3);
		} catch (BadDoorException ex) {
			System.out.println("BUG! This must never happen!");
		}

		System.out.println(c.countOpenDoors() == 1);

		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 6);
		// Change all the closed doors into open doors and vice versa:
		c.listDoors();
		// Change all the closed doors into open doors and vice versa, again:
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 1);
		// Prints 7 lines of output, each with the car's name and the fact
		// that two doors are open and five are closed.
		c.listDoors();

		// Trying to replace a nonexistent door again:
		try {
			c.replaceDoor(8);
		} catch (BadDoorException ex) {
			// We must use the equals method to compare the strings, not the ==
			// operator, because the string stored inside the exception object
			// is constructed dynamically at runtime using the + operator in
			// the replaceDoor method above.
			System.out.println(ex.getMessage().equals("Door 8 does not exist!"));
		}

		try {
			c.replaceDoor(3);
		} catch (BadDoorException ex) {
			System.out.println("BUG! This must never happen!");
		}
		System.out.println(c.countOpenDoors() == 0);
		// Prints 7 lines of output, each with the car's name and the fact
		// that all doors are closed.
		c.listDoors();
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 7);
		c.listDoors();
		c.replaceAllDoors();
		System.out.println(c.countOpenDoors() == 0);
		c.listDoors();
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 7);
		c.listDoors();
		c.replaceManyDoors(4);
		System.out.println(c.countOpenDoors() == 3);
		c.listDoors();
		try {
			c.replaceManyDoors(20);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage().equals("Index 7 out of bounds for length 7"));
		}

		System.out.println(c.countOpenDoors() == 0);
		c.listDoors();
		c.expandCar();
		c.changeAllDoors();
		System.out.println(c.countOpenDoors() == 9);
		c.listDoors();
	}
}