package question4;
// The class Animal is an abstract class.
public class Start {
	public static void main(String[] args) {
		Animal.testAnimal();
		Dog.testDog();
		Bird.testBird();
		Magpie.testMagpie();
		// No system tests: the Dog, Bird, and Magpie classes does not use any other class.
	}
}