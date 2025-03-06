package question4;

public class Start {
	public static String check(Student someStudent) {
		// TODO: Implement this method according to the question 2 description.
	}

	public static String check(Chicken someChicken) {
		// TODO: Implement this method according to the question 4 description.
	}

	public static void main(String[] args) {
		Student.testStudent();
		Chicken.testChicken();

		// Test the 'check' method using a Student.
		// We need to test both branches of the 'if' statement.
		Student s = new Student(1234567890);
		System.out.println(check(s) == "need coffee");
		s.fallAsleep();
		System.out.println(check(s) == "sweet dreams");

		// Test the other 'check' method using a Chicken.
		// We need to test both branches of the 'if' statement.
		Chicken c = new Chicken(2.3);
		System.out.println(check(c) == "sweet dreams");
		c.wakeUp();
		System.out.println(check(c) == "need coffee");
	}
}