package question4;

import java.util.Scanner;

public class CheckOddEven {
	public static void main(String[] args) {
		// Create a Scanner object
		System.out.print("Input number: ");
		// Create a Scanner object
		Scanner input = new Scanner(System.in);
		// Prompt the user to enter a number
		int number = input.nextInt();
		// Check if the number is even or odd
		try {
			if (number % 2 == 0) {
				// If the number is even
				System.out.println("Even Number");
			} else {
				// If the number is odd
				System.out.println("Odd Number");
			}
		} finally {
			// Close the Scanner object
			System.out.println("BYE!");
			input.close();
		}
	}
}
