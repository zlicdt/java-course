package question4;

public class Start {
	public static void main(String[] args) {
		System.out.println("Unit test");
		System.out.println("Test Animal");
		Animal.testAnimal();

		System.out.println("\nTest Cat");
		Cat.testCat();

		System.out.println("\nTest Dog");
		Dog.testDog();

		System.out.println("\nTest Student");
		Student.testStudent();

		System.out.println("\nSystem test");

		// Creating a new student with a cat as pet:
		System.out.println("Student s0");
		Cat c = new Cat("Meow", 2.0);
		Student s0 = new Student("Philippe", c);
		s0.getPet().setWeight(3.0);
		System.out.println("name: " + (s0.getPet().getName() == "Meow"));
		System.out.println("weight: " + (s0.getPet().getWeight() == 3.0));

		// Creating a new student with a dog as pet:
		System.out.println("\nStudent s1");
		Dog d = new Dog("Woof", 2.0);
		Student s1 = new Student("Mr. Li", d);
		s1.getPet().setWeight(3.0);
		System.out.println("name: " + (s1.getPet().getName() == "Woof"));
		System.out.println("weight: " + (s1.getPet().getWeight() == 3.0));

		// We can now also use an Animal object as a pet:
		System.out.println("\nStudent s2");
		Animal a = new Animal("Blob", 5.0);
		Student s2 = new Student("Ms. Chen", a);
		System.out.println("name: " + (s2.getPet().getName() == "Blob"));
		System.out.println("weight: " + (s2.getPet().getWeight() == 5.0));
	}

}