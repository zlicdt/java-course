package question2;

public class Start {
	public static String check(Student someStudent) {
		// TODO: Implement this method according to the question 2 description.
	}

	public static void main(String[] args) {
		Student.testStudent();

		// Test the 'check' method using a Student.
		// We need to test both branches of the 'if' statement.
		Student s = new Student(1234567890);
		System.out.println(check(s) == "need coffee");
		s.fallAsleep();
		System.out.println(check(s) == "sweet dreams");
	}
}