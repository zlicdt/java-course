package question1;

// The Animal class must be abstract because its two methods getLegs
// and canFly (below) are abstract.
public abstract class Animal {
	private String name;
	public Animal(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public abstract int getLegs();
	public abstract boolean canFly();
	public static void testAnimal() {
		// Animal a = new Animal("Unknown"); // This does not work!
	}
}