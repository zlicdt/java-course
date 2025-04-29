package question3;

public class Door {
	private boolean isOpen = false;
	public Door() {
		// Constructor: the door is closed by default.
		this.isOpen = false;
	}
	public boolean isOpen() {
		return this.isOpen;
	}
	public void open() {
		if (!this.isOpen) {
			this.isOpen = true;
		}
	}
	public void close() {
		if (this.isOpen) {
			this.isOpen = false;
		}
	}
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