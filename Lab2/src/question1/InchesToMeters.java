package question1;

import java.util.Scanner;

public class InchesToMeters {
	public static void main(String[] args) {
		// Prompt the user to enter a value for inches
		System.out.print("Input a value for inches: ");
		// Create a Scanner object
		Scanner input = new Scanner(System.in);
		// Read a value from the console
		// The value is stored in a variable of type double
		double inch = input.nextDouble();
		// Convert inches to meters
		double meter = inch * 0.0254;
		// Display the result
		System.out.println(inch + " inches is " + meter + " meters");
		// Close the Scanner
		input.close();
	}
}
