package question1;

// Dog class extends Animal class
public class Dog extends Animal {
	public Dog(String name, double weight) {
		super(name, weight);
		this.setBreed("unknown");
	}
	// Instance variable
	private String breed;
	public String getBreed() {
		return this.breed;
	}
	// Setter method
	public void setBreed(String breed) {
		this.breed = breed;
	}
	// Method to feed the dog
	public void feed() {
		this.setWeight(this.getWeight() + 2.0);
	}
}