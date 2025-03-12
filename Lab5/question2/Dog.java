package question2;

public class Dog {
	private String name;
	private String breed;
	private double weight;

	public Dog(String name, double weight) {
		this.name = name;
		this.weight = weight;
		this.breed = "unknown";
	}
	public String getName() {
		return this.name;
	}
	public String getBreed() {
		return this.breed;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public void feed() {
		this.weight += 2.0;
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
	}
}