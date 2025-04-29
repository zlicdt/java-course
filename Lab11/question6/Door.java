package question6;

public class Door {

	public static void testDoor() {
		Door d = new Door();
		// Checking that the door is closed after it has been created:
		System.out.println(d.isOpen() == false);
		// Closing a closed door does nothing:
		d.close();
		System.out.println(d.isOpen() == false);
		// Opening a closed door opens the door:
		d.open();
		System.out.println(d.isOpen() == true);
		// Opening an open door does nothing:
		d.open();
		System.out.println(d.isOpen() == true);
		// Closing an open door closes the door:
		d.close();
		System.out.println(d.isOpen() == false);
	}
}