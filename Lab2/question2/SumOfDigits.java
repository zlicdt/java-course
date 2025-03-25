package question2;

import java.util.Scanner;

public class SumOfDigits {
	public static void main(String[] args) {
		// Prompt the user to enter an integer between 0 and 1000
		System.out.print("Input an integer between 0 and 1000: ");
		// Create a Scanner object
		Scanner input = new Scanner(System.in);
		// Read a value from the console
		int sum = 0;
		// The value is stored in a variable of type int
		String str = input.nextLine();
		// Calculate the sum of all digits in the integer
		int valdatingNum = Integer.parseInt(str);
		if (valdatingNum < 0 || valdatingNum > 1000) {
			System.out.println("The number is not between 0 and 1000");
			System.exit(0);
		}
		for (int i = 0; i < str.length(); i++) {
			// Subtracting '0' from a character digit converts it to an integer digit
			sum += str.charAt(i) - '0';
		}
		// Display the result
		System.out.println("The sum of all digits in " + str + " is " + sum);
		// Close the Scanner
		input.close();
	}
}
