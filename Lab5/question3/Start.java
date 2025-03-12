package question3;

public class Start {
	public static void main(String[] args) {
		System.out.println("Unit test");
		System.out.println("Test Cat");
		Cat.testCat();

		System.out.println("\nTest Dog");
		Dog.testDog();

		System.out.println("\nTest Student");
		Student.testStudent();

		System.out.println("\nSystem test");
		Cat c = new Cat("Meow", 2.0);
		Student s = new Student("Philippe", c);
		s.getPet().feed();
		System.out.println("pet name: " + (s.getPet().getName() == "Meow"));
		System.out.println("pet weight: " + (s.getPet().getWeight() == 3.0));
	}
}