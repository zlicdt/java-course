package question4;

public class Dog extends Animal {
	public Dog(String name, double weight) {
		super(name, weight);
		this.setBreed("unknown");
	}
	private String breed;
	public String getBreed() {
		return this.breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public void feed() {
		this.setWeight(this.getWeight() + 2.0);
	}
	public static void testDog() {
		System.out.println("Test constructor");
		Dog d = new Dog("Woof", 2.0);
		System.out.println("name: " + (d.getName() == "Woof"));
		System.out.println("weight: " + (d.getWeight() == 2.0));
		System.out.println("breed: " + (d.getBreed() == "unknown"));

		System.out.println("\nTest setBreed");
		d.setBreed("Poodle");
		System.out.println("name: " + (d.getName() == "Woof"));
		System.out.println("weight: " + (d.getWeight() == 2.0));
		System.out.println("breed: " + (d.getBreed() == "Poodle"));

		System.out.println("\nTest feed");
		d.feed();
		System.out.println("name: " + (d.getName() == "Woof"));
		System.out.println("weight: " + (d.getWeight() == 4.0));
		System.out.println("breed: " + (d.getBreed() == "Poodle"));

		System.out.println("\nTest setWeight");
		d.setWeight(2.0);
		System.out.println("name: " + (d.getName() == "Woof"));
		System.out.println("weight: " + (d.getWeight() == 2.0));
		System.out.println("breed: " + (d.getBreed() == "Poodle"));
	}
}