package question1;

public class Animal {
	private String name;
	private double weight;
	// Constructor
	public Animal(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	// Methods
	public String getName() {
		return this.name;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}