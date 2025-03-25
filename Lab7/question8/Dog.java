package question8;

// The class Dog derives from the class Animal.
public class Dog extends Animal {
	public Dog(String name) {
		super(name);
	}
	@Override
	public int getLegs() {
		return 4;
	}
	@Override
	public boolean canFly() {
		return false;
	}
	public static void testDog() {
		Dog d = new Dog("Nice Doggy");
		// The getName method is inherited from Animal.
		// The getLegs and canFly methods come from Dog itself.
		System.out.println(d.getName() == "Nice Doggy");
		System.out.println(d.getLegs() == 4);
		System.out.println(d.canFly() == false);
	}
}