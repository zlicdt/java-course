package question3;

public class Student {
	private String name;
	private Cat pet;
	
	public Student(String name, Cat pet) {
		this.name = name;
		this.pet = pet;
	}
	public String getName() {
		return this.name;
	}
	public Cat getPet() {
		return this.pet;
	}
	public static void testStudent() {
		System.out.println("Test constructor");
		Cat c = new Cat("Meow", 2.0);
		Student s = new Student("Philippe", c);
		System.out.println("name: " + (s.getName() == "Philippe"));
		System.out.println("pet: " + (s.getPet() == c));
	}
}