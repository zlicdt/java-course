package question4;

public class Student {
	private String name;
	private Animal pet;
	
	public Student(String name, Animal pet) {
		this.name = name;
		this.pet = pet;
	}
	public String getName() {
		return this.name;
	}
	public Animal getPet() {
		return this.pet;
	}	
	public static void testStudent() {
		System.out.println ("Test constructor");
		Cat c = new Cat("Meow", 2.0);
		Student s0 = new Student("Philippe", c);
		System.out.println("name: " + (s0.getName() == "Philippe"));
		System.out.println("pet: " + (s0.getPet() == c));

		System.out.println ("\nTest s1's pet");
		// Creating a new student with a dog as pet:
		Dog d = new Dog("Woof", 2.0);
		Student s1 = new Student("Mr. Li", d);
		System.out.println("name: " + (s1.getName() == "Mr. Li"));
		System.out.println("pet: " + (s1.getPet() == d));

		System.out.println ("\nTest s2's pet");
		// We can now also use an Animal object as a pet:
		Animal a = new Animal("Blob", 5.0);
		Student s2 = new Student("Ms. Chen", a);
		System.out.println("name: " + (s2.getName() == "Ms. Chen"));
		System.out.println("pet: " + (s2.getPet() == a));
	}
}