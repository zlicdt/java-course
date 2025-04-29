package question1;

public class Car {
	private String name;
	private Door[] doors;

	public Car(String name, int numberOfDoors) throws BadCarException {
		if(numberOfDoors < 1) {
			throw new BadCarException("A car must have at least one door!");
		}
		this.name = name;
		this.doors = new Door[numberOfDoors];
		for (int i = 0; i < numberOfDoors; i++) {
			this.doors[i] = new Door();
		}
	}
	public void listDoors() {
		for (Door d : doors) {
			if(d.isOpen()) {
				System.out.println(this.name + ": door is open");
			} else {
				System.out.println(this.name + ": door is closed");
			}
		}
	}
	public int countOpenDoors() {
		int count = 0;
		for (Door d : doors) {
			if(d.isOpen()) {
				count++;
			}
		}
		return count;
	}
	public void openOneDoor(int doorNumber) throws BadDoorException {
		if(doorNumber <= 0 || doorNumber >= this.doors.length) {
			throw new BadDoorException("Door " + doorNumber + " does not exist!");
		}
		this.doors[doorNumber].open();
	}
	public static void testCar() {
		// Trying to create a car with the wrong number of doors:
		try {
			Car brokencar = new Car("Broken", 0);
		} catch (BadCarException ex) {
			// We can use the == operator here to compare strings because both strings//
			// (the one stored inside the exception object, and the one used directly// in
			// the test itself) are constant strings. The string stored insidethe//
			// exception object and returned as result by the getMessage method isa//
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
		// Trying to open a nonexistent door:
		try {
			c.openOneDoor(0);
		} catch (BadDoorException ex) {
			// We must use the equals method to compare the strings, not the ==
			// operator, because the string stored inside the exception object
			// is constructed dynamically at runtime using the + operator in
			// the openOneDoor method above.
			System.out.println(ex.getMessage().equals("Door 0 does not exist!"));
		}
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
		// and 5:
		try {
			c.openOneDoor(5);
		} catch (BadDoorException ex) {
			System.out.println("BUG! This must never happen!");
		}
		System.out.println(c.countOpenDoors() == 2);
		// Prints 7 lines of output, each with the car's name and the fact
		// that two doors are open and five are closed.
		c.listDoors();
	}
}