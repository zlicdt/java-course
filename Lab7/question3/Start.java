package question3;
// The class Animal is an abstract class.
public class Start {
	public static void main(String[] args) {
		Animal.testAnimal();
		Dog.testDog();
		Bird.testBird();
		// No system tests: the Dog and Bird classes does not use any
		// other class.
	}
}