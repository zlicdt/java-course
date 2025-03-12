package question4;

public class Cat extends Animal {
	public Cat(String name, double weight) {
		super(name, weight);
	}
	public void feed() {
		this.setWeight(this.getWeight() + 1.0);
	}

	public static void testCat() {
		System.out.println("Test constructor");
		Cat c = new Cat("Meow", 2.0);

		// The getName and getWeight methods are inherited from Animal.
		System.out.println("name: " + (c.getName() == "Meow"));
		System.out.println("weight: " + (c.getWeight() == 2.0));

		System.out.println("\nTest feed");
		c.feed();
		// The name is still the same but the weight increased by 1.0:
		System.out.println("name: " + (c.getName() == "Meow"));
		System.out.println("weight: " + (c.getWeight() == 3.0));

		// The setWeight method is inherited too.
		System.out.println("\nTest setWeight");
		c.setWeight(2.0);
		System.out.println("name: " + (c.getName() == "Meow"));
		System.out.println("weight: " + (c.getWeight() == 2.0));
	}
}