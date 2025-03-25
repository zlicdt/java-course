package question5;
// The class Animal is an abstract class.
public class Start {
	public static void main(String[] args) {
		Animal.testAnimal();
		Dog.testDog();
		Bird.testBird();
		Magpie.testMagpie();
		Ostrich.testOstrich();
		// No system tests: the Dog, Bird, Magpie, and Ostrich classes
		// does not use any other class.
	}
}