package question1;

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
}