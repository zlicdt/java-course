package question6;
// The class Animal is an abstract class.
public class Start {
	public static void main(String[] args) {
		Animal.testAnimal();
		Dog.testDog();
		Bird.testBird();
		Magpie.testMagpie();
		Ostrich.testOstrich();
		Pegasus.TestPegasus();
		// No system tests: the Dog, Bird, Magpie, Ostrich, and Pegasus
		// classes does not use any other class.
	}
}