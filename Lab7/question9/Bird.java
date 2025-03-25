package question9;

// The class Bird derives from the class Animal.
// The class must be abstract because it does not have any code for
// the canFly method (see below).
public abstract class Bird extends Animal implements Flyer{
	private int numOfEggs;
	public Bird(String name) {
		super(name);
	}
	public void setNumOfEggs(int numOfEggs) {
		this.numOfEggs = numOfEggs;
	}
	public int getNumOfEggs() {
		return numOfEggs;
	}
	@Override
	public int getLegs() {
		return 2;
	}
	public boolean isDangerous() {
		return false;
	}
	// The getLegs method is inherited from Animal.
	public static void testBird() {
		// Bird b = new Bird("Twitter", 3); // This does not work!
	}
}